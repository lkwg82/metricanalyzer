/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.company.api.ArticleDTO;
import biz.company.api.ArticleDTOList;
import biz.company.api.BasketDTO;
import biz.company.api.CurrencyDTO;
import biz.company.api.CurrencyDTOList;
import biz.company.api.DesignDTOList;
import biz.company.api.FontFamilyDTOList;
import biz.company.api.ObjectFactory;
import biz.company.api.PrintTypeDTOList;
import biz.company.api.ProductDTO;
import biz.company.api.ProductTypeDTOList;
import biz.company.api.Reference;
import biz.company.api.ShippingTypeDTOList;
import biz.company.api.ShopDTO;

/**
 * based on
 * https://github.com/company-dev/companyapps/blob/master/java/samplebrowser/src/main/java/biz.company/sampleapps
 * /common/dataaccess/CompanyAPI.java
 * 
 
 
 */
public class CompanyAPI {

    private static final Logger log = LoggerFactory.getLogger(CompanyAPI.class);

    private ShopDTO shop;
    private ObjectFactory objectFactory;
    private HttpUrlConnectionFactory urlConnectionFactory;
    private HttpCallCommandFactory factory;

    private Configuration config;

    /**
     * @param config
     */
    public CompanyAPI(final Configuration config) {
        try {
            this.config = config;
            objectFactory = new ObjectFactory();
            urlConnectionFactory = new HttpUrlConnectionFactory(config.getAPIKey(), config.getSecret(), "none");
            factory = new HttpCallCommandFactory(urlConnectionFactory, "biz.company.api");
            shop = getShop();

        } catch (Exception e) {
            throw new CompanyAPIException("Could not start CompanyAPI!", e);
        }
    }

    private HttpCallCommand doGetCall(final String url) {
        HttpCallCommand command = factory.createJaxbHttpCallCommand(url, HttpMethod.GET, null);
        command.execute();
        return command;
    }

    private <T> T getCastedValueFromGetCall(final String url, final Class<T> clazz, final String errorMessage) {
        return getCastedValueFromGetCall(url, clazz, errorMessage, false);
    }

    private <T> T getCastedValueFromGetCall(final String url, final Class<T> clazz, final String errorMessage,
            final boolean apiKeyProtected) {
        HttpCallCommand command = doGetCall(url);
        command.setApiKeyProtected(apiKeyProtected);

        if (command.getStatus() != 200) {
            throw new IllegalArgumentException(errorMessage);
        } else {
            @SuppressWarnings("rawtypes")
            Object value = ((JAXBElement) command.getOutput()).getValue();
            return clazz.cast(value);
        }
    }

    public ArticleDTO getArticle(final String articleId) {
        String errorMessage = "Articles data not available";
        String url = shop.getArticles().getHref() + "/" + articleId;
        return getCastedValueFromGetCall(url, ArticleDTO.class, errorMessage);
    }

    public ProductDTO getProduct(final String productId) {
        String errorMessage = "Products data not available";
        String url = shop.getProducts().getHref() + "/" + productId;
        return getCastedValueFromGetCall(url, ProductDTO.class, errorMessage);
    }

    public ArticleDTOList getArticles() {
        String errorMessage = "Articles data not available";
        String url = shop.getArticles().getHref() + "?fullData=true&limit=200";
        return getCastedValueFromGetCall(url, ArticleDTOList.class, errorMessage);
    }

    public ProductTypeDTOList getProductTypes() {
        String url = shop.getProductTypes().getHref() + "?fullData=true&limit=1000";
        String errorMessage = "ProductTypes data not available";
        return getCastedValueFromGetCall(url, ProductTypeDTOList.class, errorMessage);
    }

    public PrintTypeDTOList getPrintTypes() {
        String url = shop.getPrintTypes().getHref() + "?fullData=true&limit=1000";
        String errorMessage = "PrintTypes data not available";
        return getCastedValueFromGetCall(url, PrintTypeDTOList.class, errorMessage);
    }

    public FontFamilyDTOList getFontFamilies() {
        String url = shop.getFontFamilies().getHref() + "?fullData=true&limit=1000";
        String errorMessage = "FontFamilies data not available";
        return getCastedValueFromGetCall(url, FontFamilyDTOList.class, errorMessage);
    }
    
    public ShippingTypeDTOList getShippingTypes() {
        String url = shop.getShippingTypes().getHref() + "?fullData=true&limit=1000";
        String errorMessage = "ShippingTypes data not available";
        return getCastedValueFromGetCall(url, ShippingTypeDTOList.class, errorMessage);
    }

    public ShopDTO getShop() {
        String url = config.getShopUrl();
        String errorMessage = ("Shop data not available");
        return getCastedValueFromGetCall(url, ShopDTO.class, errorMessage);
    }

    public CurrencyDTO getShopCurrency() {
        String url = getShop().getCurrency().getHref();
        String errorMessage = "Shop data not available";
        return getCastedValueFromGetCall(url, CurrencyDTO.class, errorMessage);
    }

    public DesignDTOList getDesigns() {
        String url = shop.getDesignCategories().getHref() + "/b1000000/designs?fullData=true&offset=0&limit=100";
        String errorMessage = "Desgisn data not available";
        return getCastedValueFromGetCall(url, DesignDTOList.class, errorMessage);
    }

    public CurrencyDTOList getCurrencies() {
        String url = getShop().getCurrencies().getHref() + "?fullData=true";
        String errorMessage = "Shop data not available";
        return getCastedValueFromGetCall(url, CurrencyDTOList.class, errorMessage);
    }

    public BasketDTO createBasket() {
        BasketDTO basket = objectFactory.createBasketDTO();
        basket.setToken(UUID.randomUUID().toString());
        /*
         * Reference shopRef = objectFactory.createReference(); shopRef.setId(shop.getId()); basket.setShop(shopRef);
         */
        HttpCallCommand command = factory.createJaxbHttpCallCommand(shop.getBaskets().getHref(), HttpMethod.POST, null);
        command.setApiKeyProtected(true);
        command.setInput(objectFactory.createBasket(basket));
        command.execute();
        log.info(command.getLocation());
        log.info("" + command.getStatus());
        log.info(command.getErrorMessage());
        if (command.getStatus() != 201) {
            throw new IllegalArgumentException("Could not create Basket!");
        }
        log.info("Basket location is: " + command.getLocation());
        return getBasket(command.getLocation().substring(command.getLocation().lastIndexOf("/") + 1));
    }

    public BasketDTO getBasket(final String basketId) {
        String errorMessage = "Could not retrieve basket!";
        String url = shop.getBaskets().getHref() + "/" + basketId;
        return getCastedValueFromGetCall(url, BasketDTO.class, errorMessage, true);
    }

    public void updateBasket(final BasketDTO basket) {
        HttpCallCommand command = factory.createJaxbHttpCallCommand(shop.getBaskets().getHref() + "/" + basket.getId(),
                HttpMethod.PUT, null);
        command.setApiKeyProtected(true);
        command.setInput(objectFactory.createBasket(basket));
        command.execute();
        if (command.getStatus() != 200) {
            throw new IllegalArgumentException("Could not create Basket!");
        }
    }

    public String getBasketCheckoutUrl(final String id) {
        String errorMessage = "Could not retrieve checkout reference!";
        String url = shop.getBaskets().getHref() + "/" + id + "/checkout";
        return getCastedValueFromGetCall(url, Reference.class, errorMessage, true).getHref();

    }
}
