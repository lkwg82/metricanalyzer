/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.api;

import java.util.Iterator;

import biz.company.api.ProductTypeDTO;
import biz.company.api.ProductTypeStockStateDTO;
import biz.company.api.ShippingCostDTO;
import biz.company.api.ShippingCountryDTO;
import biz.company.api.ShippingRegionDTO;
import biz.company.api.ShippingTypeDTO;
import biz.company.api.ShippingTypeDTOList;

import biz.company.qa.pageobjects.utils.Price;

/**
 
 */
public class CompanyAPIHelper {
    private final CompanyAPI api;

    /**
     * @param api
     */
    public CompanyAPIHelper(final CompanyAPI api) {
        this.api = api;
    }

    /**
     * @param productTypeId
     * @return
     */
    public ProductTypeDTO findProductTypeById(final int productTypeId) {

        for (ProductTypeDTO p : api.getProductTypes().getProductType()) {
            if (String.valueOf(productTypeId).equals(p.getId())) {
                return p;
            }
        }

        throw new CompanyAPIException("could not find productType by id:" + productTypeId);
    }

    public ProductTypeStockStateDTO findFirstAvailableStockstate(final ProductTypeDTO productType) {

        for (ProductTypeStockStateDTO s : productType.getStockStates().getStockState()) {
            if (s.isAvailable()) {
                return s;
            }
        }
        throw new CompanyAPIException("could not find " + ProductTypeStockStateDTO.class
                + " for  productType with id : " + productType.getId());
    }

    /**
     * Find the right shipping price for a given set of type, country and article price
     */
    public Price getShippingCost(String countryName, String shippingTypeName, Price price,
            ShippingTypeDTOList shippingTypes) {
        // find a shipping type which matches by both name and country
        for (Iterator<ShippingTypeDTO> i = shippingTypes.getShippingType().iterator(); i.hasNext();) {
            ShippingTypeDTO currentType = i.next();
            if (currentType.getName().equals(shippingTypeName)) {
                for (Iterator<ShippingCountryDTO> it = currentType.getShippingCountries().getShippingCountry()
                        .iterator(); it.hasNext();) {
                    ShippingCountryDTO currentCountry = it.next();
                    // country
                    if (currentCountry.getName().equals(countryName)) {
                        for (Iterator<ShippingRegionDTO> iter = currentType.getShippingRegions().getShippingRegion()
                                .iterator(); iter.hasNext();) {
                            ShippingRegionDTO currentRegion = iter.next();
                            // look for the region
                            if (currentRegion.getId().equals(currentCountry.getShippingRegion().getId())) {
                                for (Iterator<ShippingCostDTO> iterator = currentRegion.getShippingCosts()
                                        .getShippingCost().iterator(); iterator.hasNext();) {
                                    ShippingCostDTO currentShippingCost = iterator.next();
                                    // shipping cost (almost there!)
                                    if (currentShippingCost.getOrderValueRange().getFrom().doubleValue() <= price
                                            .getValue()
                                            && currentShippingCost.getOrderValueRange().getTo().doubleValue() >= price
                                                    .getValue()) {
                                        return Price.fromApiPrice(currentShippingCost.getCost(), api.getShopCurrency());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        // still with us? ok, seems we did not find anything
        throw new RuntimeException("Could not find a mathcing price");
    }
}
