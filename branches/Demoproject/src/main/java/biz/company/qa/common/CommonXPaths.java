package biz.company.qa.common;

public class CommonXPaths {

  /**
   * A collection of xpaths - hopefully these will make the code more readable.
   */
  public CommonXPaths() {
  }

  public final static String QA_PREMIUM_SHOP_ID = "282267";
  public final static String QA_PREMIUM_SHOP_ID_VANGOGH = "371278";
  public final static String QA_STANDARD_SHOP_ID = "282258";
  public final static String QA_PREMIUM_ACCOUNT_ID = "1102730";

  /**
   * General
   */
  public static final String CANONICAL_URL_TAG = "//*[@rel='canonical']";
  public static final String CANONICAL_URL = CANONICAL_URL_TAG + "@href";

  public final static String SYSTEM_MESSAGE_BY_ID = "//*[@id='system_msg']";
  public final static String SYSTEM_MESSAGE_BY_CLASS = "//*[@class='system-message']";

  public final static String SYSTEM_MESSAGE_INFO_BY_ID = "//*[@id='system_msg']";
  public final static String SYSTEM_MESSAGE_INFO_BY_CLASS = "//*[contains(@class,'message info')]";

  public final static String SYSTEM_MESSAGE_SUCCESS_BY_ID = "//*[@id='successMessage']";
  public final static String SYSTEM_MESSAGE_ERROR_BY_ID = "//*[@id='errorMSG']";
  public final static String SYSTEM_ERROR_BY_CLASS = "//*[contains(@class, 'error')]";

  public final static String TOP_LANG = "//*[@id='countrySelection']/select";
  public final static String BASKET_WITH_ARTICLE = "//*[contains(@class, 'basket-active')]";

  /**
   * navigation on SELL page
   */
  public final static String SELL_OPEN_company_SHOP = "navi-2-57";
  public final static String SELL_SPREADUCATION = "navi-2-3553";
  /**
   * Marketplace locations
   */
  public final static String MP_ADD_TO_BASKET = "//*[@value='In den Warenkorb']";
  public final static String MP_ARTICLE_WRAPPER = "//div[contains(@class, 'article_wrapper')]";
  public final static String MP_PAGE_TITLE = "//*[@id='content']//h1";
  public final static String MP_PRODUCT_TITLE = MP_ARTICLE_WRAPPER + "//span";
  public final static String MP_PRODUCT_ATTRIBUTE = MP_ARTICLE_WRAPPER + "//img@id";
  public final static String MP_DETAIL_PAGE_PRODUCT_TITLE = "//*[@id='marketplace-product-description']/h1";
  public final static String MP_DETAIL_PAGE_PRODUCT_DESCRIPTION = "//*[@id='articleInfo']/p";
  public final static String MP_DETAIL_PAGE_PRODUCT_DESIGNER = "//*[@id='user_info1']//strong";
  public final static String MP_DETAIL_PAGE_PRODUCT_DESIGNER_LINK = "//*[@id='user_info1']//a";
  public final static String MP_DETAIL_PAGE_PRODUCTTYPE = "//*[@id='marketplace-product-description']/h2";
  public final static String MP_CUSTOMIZE_IT_BUTTON = "//*[@id='customize-it']/a";
  public final static String MP_PAGER = "//*[contains(@class, 'pager')]//*[@class='first']";
  public final static String MP_PRODUCT_IMAGE_COLOUR = "//*[@id='marketplace_product_images']//img[contains(@src, 'producttypecolor')]";
  public final static String MP_SEARCH_BUTTON = "//button[@type='submit']";
  public final static String MP_NAVIGATION_SEARCH_BUTTON = "//*[@id='searchRegular']";
  public final static String MP_SEARCH_FORM = "//input[@id='navigation-search-box']";
  public final static String MP_STARTPAGE = "//*[@id='navi-1-2']/span";
  public final static String MP_FIRST_DEPARTMENT = "//*[@id='navi-2-4409']";
  public final static String MP_FIRST_PRODUCTTYPE = "//li[contains(@class, 'facet_entry')]/a[contains(@href, 'P')]";
  public final static String MP_FIRST_THEME_MENU = "//*[@class='facet_entry_unselected']/a";
  public final static String MP_CONFOMAT = "navi-1-6078";
  public final static String MP_TOP_BASKET_LINK = "//*[@id='minibasket']//a";
  public final static String MP_SELL_OPEN_SHOP_BUTTON = "//a[@class='large-prio-1-button']";
  public final static String MP_SELECT_SIZE = "//*[@id='selectedSize']";
  public final static String MP_MEN_LONG_SLEEVE = "/de/DE/-/Liste-4410/Marketplace/Products/list/department/1/category/25/";
  public final static String MP_DESIGNS_PATH = "//*[@class='products-n-designs-list designs-low']/li";
  public final static String MP_LARGE_WIDGET = "//*[@class='marketplace-product-wrapper']/div/a";
  public final static String MP_SMALL_WIDGET = "//*[@class='text-right']/p/a";
  public final static String MP_SMALL_WIDGET_PRODUCT = "//*[@class='product-and-design']";
  public final static String MP_LARGE_WIDGET_DESIGNER_LINK = "//*[contains(@id, 'owner')]/a";
  public final static String MP_DEPARTMENT_WIDGET_ONE = "//*[@id='www-content']/div[1]/div[5]/a";
  public final static String MP_DEPARTMENT_WIDGET_TWO = "//*[@id='www-content']/div[1]/div[8]/a";

  public final static String MP_POPULAR_TOPICS = "//*[@class='prio-2-button right']";
  public final static String MP_ALL_DEPARTMENT_TOPICS = "//*[@class='left department-theme-category-menu-wrapper']/p/a";
  public final static String MP_MEN_TOPIC_LINK = "//*[@id='department-theme-category-menu']/li/a[contains(@rel,'track[dept:Männer,topic,";
  public final static String MP_MEN_PRODUCTTYPE_LINK = "//*[@id='producttype-category-menu']/li/a[contains(@rel,'track[dept:Männer,ptc,";
  public final static String MP_WOMEN_TOPIC_LINK = "//*[@id='department-theme-category-menu']/li/a[contains(@rel,'track[dept:Frauen,topic,";
  public final static String MP_WOMEN_PRODUCTTYPE_LINK = "//*[@id='producttype-category-menu']/li/a[contains(@rel,'track[dept:Frauen,ptc,";
  public final static String MP_KIDS_TOPIC_LINK = "//*[@id='department-theme-category-menu']/li/a[contains(@rel,'track[dept:Kinder & Babys,topic,";
  public final static String MP_KIDS_PRODUCTTYPE_LINK = "//*[@id='producttype-category-menu']/li/a[contains(@rel,'track[dept:Kinder & Babys,ptc,";
  public final static String MP_ACCESSORIES_PRODUCTTYPE_LINK = "//*[@id='producttype-category-menu']/li/a[contains(@rel,'track[dept:Accessoires,ptc,";
  public final static String MP_ACCESSORIES_TOPIC_LINK = "//*[@id='department-theme-category-menu']/li/a[contains(@rel,'track[dept:Accessoires,topic,";

  public final static String CREAT_YOUR_OWN_TEXT = "//*[@class='triple-col-box']/p/a";
  public final static String MP_FIRST_ARTICLE_NAME = "//*[@class='product-and-design']/span";
  public final static String MP_DEPARTMENT_FILTER = "//*[@class='box']//*[.='Produkte']/..//a[contains(@class, 'current')]";
  public final static String MP_DESIGN_CATEGORY_FILTER = "xpath=(//*[@class='box']//a[contains(@class, 'current_nested')])[2]";
  /** Marketplace departments **/
  /* List of the top-level navigation, add link path afterwards */
  public final static String MP_DEPARTMENTS_LIST = "//*[@id='top-navi-list']/li/";
  public final static String MP_DEPARTMENT_MEN = "navi-2-4409";
  public final static String MP_DEPARTMENT_WOMEN = "navi-2-4413";
  public final static String MP_DEPARTMENT_KIDS_N_BABIES = "navi-2-4417";
  public final static String MP_DEPARTMENT_ACCESSORIES = "navi-2-4421";

  /* Product details page */
  public final static String MP_DESIGN_THUMB = "//*[@id='designs']/div/div/img";
  public final static String MP_DETAILS_PAGE_DESIGN_ID = "//*[@id='designs']/div/div/div/p";
  public final static String MP_SIZE_TABLE_LINK = "//*[@id='choose-product-size']/p/a/@href";
  public final static String MP_FIRST_COLOUR_ENTRY = "//*[@id='ColorBarEntries']/li/a/@id";
  public final static String MP_FIRST_SIZE_ENTRY = "//*[@id='SizesBarEntries']/li/a@id";

  /* Community Profile page */
  public final static String MP_PROFILE_DESIGNS_LIST = "//*[@class='products-n-designs-list my-favorotes-designs']";
  public final static String MP_PROFILE_DESIGNS_GRAB = "//a[contains(., 'Zu meinen Motiven hinzufügen')]";
  public final static String MP_PROFILE_DESIGNS_CREATE_PRODUCT = "//a[@class='create-product with-block-icon display-block']";

  /**
   * Shop
   */
  public final static String SHOP_NAME = "//*[@id='shopInformation']/h1";
  public final static String SHOP_PRODUCT_DETAILS = "//*[@id='frmArticleDetails']";
  public final static String SHOP_PRODUCT_DETAILS_PAGE = "//a[contains(@href, '/Shop/Article/Index/article/')]";
  public final static String SHOP_FOOTER = "//*[@id='menuFooter']//a";
  public final static String SHOP_HEADER = "//*[@id='headerUserInformation']";
  public final static String SHOP_PRODUCTS_LIST = "//*[@id='index']//";
  public final static String SHOP_BODY = "//*[@id='main']";
  public final static String SHOP_HEADER_LEFT = SHOP_HEADER + "/ul[@class='left']";
  public final static String SHOP_HEADER_RIGHT = SHOP_HEADER + "/ul[@class='right']";
  public final static String SHOP_ADD_TO_BASKET = "//*[@value='In den Warenkorb']";
  public final static String SHOP_TOP_BASKET_LINK = "link=Warenkorb";
  public final static String SHOP_TOP_USERNAME = "//li[@id='userEnable']/strong";
  public final static String SHOP_TOP_LOGIN = "//*[@id='userDisable']/a";
  public final static String SHOP_ACTIVATE = "link=Aktivieren";
  public final static String SHOP_LEGAL_INFORMATION = "link=Impressum";
  public final static String SHOP_404_LINK_VISIT = "//li[@class='listMCE']//a[contains(@href,'http://www.')]";
  public final static String SHOP_404_LINK_CREATE_SHIRT = "link=Selbst ein T-Shirt gestalten";
  public final static String SHOP_404_LINK_OPEN_SHOP = "link=Deinen eigenen Company-Shop eröffnen";
  public final static String SHOP_404_BUTTON_CREATE_SHIRT = "link=Jetzt ein T-Shirt selbst gestalten";
  public final static String SHOP_404_BUTTON_OPEN_SHOP = "link=Jetzt eigenen Shop eröffnen";
  public final static String SHOP_404_EMAIL = "//a[@href='mailto:what@company.biz']";
  public final static String SHOP_NEXTPAGE = "//a[@title='Vorwärts']";
  public final static String SHOP_ARTICLE_ID = "//*[@class='articleDesc']//div[4]/span";
  public final static String SHOP_NAVI_SHOPTAB = "//a[@title='Shop']";
  public final static String SHOP_ARTICLE_FIRST_COLOUR_ID = "//*[contains(@id, 'colorEntry')]@id";
  public final static String SHOP_ARTICLE_FIRST_SIZE_ID = "//*/select[@class='articleSize']/option[contains(@id,'colorSize0')]@value";
  public final static String SHOP_ARTICLE_MEASURE_TABLE = "//*[@id='articleMeasureLink']";
  public final static String SHOP_ARTICLE_SIZE_SELECT = "//*/select[@id='articleSize']/option[contains(@value,'";
  public final static String SHOP_ARTICLE_COLOUR_SELECT = "//select[@id='articleColor']/option[contains(@value,'";
  public final static String SHOP_SIZE_SELECT_IN_BASKET = "//span/select[@name='basketitem[size]']/option[contains(@value,'";
  public final static String SHOP_SIGNUP_BUTTON = "//*[@value='Anmelden']";
  public final static String SHOP_POPUP_HEADER = "//*[@id='help']/h2";
  public final static String SHOP_METADATA_INDEX = "//meta[@name='robots' and contains(@content,'index, follow')]";
  public final static String SHOP_METADATA_NO_INDEX = "//meta[@name='robots' and contains(@content,'noindex, nofollow')]";
  public final static String SHOP_PRODUCT_NAME = "//form[@id='frmArticleDetails']/div/h3";
  public final static String SHOP_PRODUCT_DESCRIPTION = "//form[@id='frmArticleDetails']/div/p";
  public final static String SHOP_PRODUCT_PTYPE = "//form[@id='frmArticleDetails']/div/h5";
  public final static String SHOP_FIRST_DESIGN = "//*[@id='articleDesigns']/div[1]/h3";

  /**
   * User area
   */
  public final static String UA_IBOX = "//*[@id='i_con']";
  public final static String UA_APPROVE_CHANGES_POPUP = "//*[@id='i_con']//*[@title='Speichern']";
  public final static String UA_INVALID_COMMISSION = "//*[@id='invalidCommission']";
  public final static String UA_SAVE_NEW_COMMISSION = "//*[@id='i_con']//a[@title='Speichern']";
  public final static String UA_ACCOUNT_TOTAL = "//*[@class='summary']/td[contains(@class,'text_right')]";

  // Settings
  public final static String UA_SAVE_CURRENCY_COUNTRY = "//form[@id='updateLocCurr_form']/fieldset[3]/a";
  public final static String UA_LANGUAGE_DROP_DOWN_BASE = "//*[@id='updateLang_form']/div[1]/ul/li/select/option[";
  public final static String UA_CANT_CHANGE_SHOP_CURRENCY = "//form[@id='updateLocCurr_form']/div";

  // Product overview page
  public final static String UA_PO_NEW_PRODUCT_BUTTON = "link=Neues Produkt";
  public final static String UA_PO_EDIT_PRODUCT_LINK = "//a[@class='edit-product with-block-icon display-block']";
  public final static String UA_PO_EDIT_MP_SETTINGS_LINK = "//*[@class='marketplace-product-settings with-block-icon display-block']";
  public final static String UA_PO_NEXTPAGE = "link=Zur nächsten Seite springen";
  public final static String UA_PO_PREVIOUSPAGE = "link=Zur vorhergehenden Seite springen";
  public final static String UA_PO_FIRSTID_LABEL = "//*[@class='user-product-list']//dd";
  public final static String UA_PRODUCT_DETAILS = "//*[@class='editBasketItem']@href";

  public final static String UA_PO_BULK_EDIT_FORM = "//form[@id='bulk-edit']";
  public final static String UA_PO_BULK_EDIT_CATEGORY_SELECT = "batch[category]_0_main";
  public final static String UA_PO_BULK_EDIT_SUBCATEGORY_SELECT = "//select[@id='batch[category]_0']";
  public final static String UA_PO_BULK_EDIT_SAVE_BUTTON = "//input[@value='Speichern']";
  public final static String UA_PO_BULK_EDIT_CANCEL_BUTTON = "link=Abbrechen";
  public final static String UA_PO_BULK_ACTION_SELECT = "//select[@name='do_action']";
  public final static String UA_CONFIRM_MP_REMOVAL_LINK = "//*[@value='Produkt entfernen']";
  public static final String UA_PO_CONFIRM_DELETE_BUTTON = "delete";
  public static final String UA_PO_CANCEL_DELETE_BUTTON = "link=Abbrechen";
  public final static String UA_PO_DETAILS_LINK = "//a[text()='Details']";

  // Same Xpath for Product and Design Overview
  public final static String UA_OVERVIEW_ITEMLIST = "//*[@id='designForm']";
  public final static String UA_OVERVIEW_ITEM = UA_OVERVIEW_ITEMLIST + "/fieldset";
  public final static String UA_OVERVIEW_ITEMACTIONS = "//ul[@class='list-of-actions']";
  public final static String UA_OVERVIEW_DELETE_ITEM_LINK = "//a[contains(@href,'/Designs/delete/design')]";
  public final static String UA_OVERVIEW_ADD_TO_SHOP_LINK = "//a[contains(@href,'addToDesignershopsSelect')]";
  public final static String UA_OVERVIEW_PUBLISH_ON_MP_LINK = "//a[contains(@href,'marketplaceOptions')]";
  public final static String UA_NUMBER_OF_RESULTS_LABEL = "//li[@class='third']/label";
  public final static String UA_OVERVIEW_SHOPLIST = "//ul[@class='list-of-shops']";
  public final static String UA_OVERVIEW_SHOPLINK = "//a[@class='url-link']";

  // Design Overview Page
  public final static String UA_DO_DETAILS_LINK = "//a[text()='Details ']";
  public final static String UA_DO_INFOSECTION = "//div[@class='design-infos user-design-infos no-margin-left']/dl";
  public final static String UA_DO_CREATE_PRODUCT_LINK = "//a[contains(@href,'confomat')]";
  public final static String UA_DO_EDIT_MP_SETTINGS_LINK = "//a[contains(@title, 'Marktplatzeinstellungen bearbeiten')]";
  public final static String UA_DO_WAIT_FOR_APPROVAL_LINK = "//*[contains(@class, 'marketplace-design-settings') and contains(.,'Wird gerade geprüft')]";
  public final static String UA_DO_SEARCH_DESIGNID_INPUT = "s_name";

  // Design Sort Page
  public final static String UA_DESIGN_SORT_TOBOTTOM = "//a[contains(@class,'sort_bottom')]";
  public final static String UA_DESIGN_SORT_TOTOP = "//a[contains(@class,'sort_top')]";
  public final static String UA_DESIGN_SORT_UP = "//a[contains(@class,'sort_up')]";
  public final static String UA_DESIGN_SORT_DOWN = "//a[contains(@class,'sort_down')]";

  public final static String UA_PROFILE_PARTNERSTATUS = "//*[@id='account']//th[.='Status']/../td";
  public final static String UA_PROFILE_DESIGN_RSS = "//*[contains(., 'Motive')]/a[contains(@class, 'rss')]";
  public final static String UA_PROFILE_ARTICLE_RSS = "//*[contains(., 'Produkte')]/a[contains(@class, 'rss')]";

  public final static String UA_SHOPASSISTANT_STEP1 = "//*[@id='wizz-step-1']/a";
  public final static String UA_SHOPASSISTANT_STEP2 = "//*[@id='wizz-step-2']/a";
  public final static String UA_SHOPASSISTANT_STEP3 = "//*[@id='wizz-step-3']/a";
  public final static String UA_SHOPASSISTANT_STEP4 = "//*[@id='wizz-step-4']/a";
  public final static String UA_SHOPASSISTANT_STEP5 = "//*[@id='wizz-step-5']/a";
  public final static String UA_SHOPASSISTANT_TITLE1 = "Schritt 1 - Assistent zum Einrichten des Shops | Company";
  public final static String UA_SHOPASSISTANT_TITLE2 = "Schritt 2 - Assistent zum Einrichten des Shops | Company";
  public final static String UA_SHOPASSISTANT_TITLE3 = "Schritt 3 - Assistent zum Einrichten des Shops | Company";
  public final static String UA_SHOPASSISTANT_TITLE4 = "Schritt 4 - Assistent zum Einrichten des Shops | Company";
  public final static String UA_SHOPASSISTANT_TITLE5 = "Schritt 5 - Assistent zum Einrichten des Shops | Company";
  public final static String UA_SAVE_BUTTON = "//*[@value='Speichern']";

  public final static String UA_SHOP_SUBSCRIPTION_NAME = "//*[@id='subscriptions-shops']//p";
  public final static String UA_SHOP_SUBSCRIPTION_STATUS = "xpath=(//*[@id='subscriptions-shops']//input)[2]@value";
  public final static String UA_SHOP_SAVE_BUTTON = "//*[@id='shop-list']//input[@type='submit']";
  public final static String UA_SHOP_SHOPLIST = "//*[@id='shop-list']";

  /** Shop newsletter **/
  public final static String UA_NL_BECOME_PREMIUM_PARTNER_LINK = "//a[contains(.,'Premium-Mitglied')]";
  public final static String UA_NL_DEACTIVATED_SECTION = "//*[@class='section deactivated-all-inside']";
  public final static String UA_NL_SECTION_EXPANDED = "//*[@class='newsletter-options']";

  public final static String UA_NL_LAYOUT_EDIT_BUTTON = "btnEditLayout";
  public final static String UA_NL_LAYOUT_SAVE_BUTTON = "//*[@id='layoutSettings']//*[@value='Speichern']";
  public final static String UA_NL_LAYOUT_CLOSE_BUTTON = "//*[@id='layoutSettings']//*[@value='Schließen']";

  public final static String UA_NL_AUTO_EDIT_BUTTON = "//*[@id='btnCloseAuto1']";
  public final static String UA_NL_AUTO_ACTIVATE_BUTTON = "btnActivateAuto";
  public final static String UA_NL_AUTO_CLOSE_BUTTON = "//*[@id='autoSettings']//*[@value='Schließen']";
  public final static String UA_NL_AUTO_PRODUCTS_LIST = "//*[@id='autoSettings']//div[1]//ul[@class='thumbimg-list prod-des-thumbs left']";
  public final static String UA_NL_AUTO_DESIGNS_LIST = "//*[@id='autoSettings']//div[@class='triple-col-box light-col medium-gap-up']//ul[@class='thumbimg-list prod-des-thumbs left']";
  public final static String UA_NL_AUTO_DESIGN_IMAGE = "xpath=(//img[contains(@src, 'image-server/image/design')])[";

  public final static String UA_NL_MANUAL_EDIT_BUTTON = "//*[@id='manualSettingsEdit']/a";
  public final static String UA_NL_MANUAL_SEND_BUTTON = "link=Manuellen Newsletter versenden";
  public final static String UA_NL_MANUAL_CLOSE_BUTTON = "//*[@id='manualSettings']//*[@value='Schließen']";
  public final static String UA_NL_MANUAL_ITEMS_LIST = "//*[@id='manualSettings']//li";

  public final static String UA_NL_POPUP_CURRENTPAGE = "//*[@class='pager']//a[@class='current']";

  /**
   * MarthaB
   */
  public final static String UA_FIRST_DESIGN_CATRGORY = "//form[@id='frmDesignCategories']//*[contains(@class,'metatext')]";
  public final static String UA_DESIGN_CATEGORY_BULK = "//select[@name='do_action']";
  public final static String UA_MOVE_DESIGN_TO_CATEGORY = "//select[@id='bulk_action']/option[contains(@value, 'move_to_category')]";
  public final static String UA_REMOVE_DESIGN_FROM_CATEGORY = "//select[@id='bulk_action']/option[contains(@value, 'remove_from_category')]";
  public final static String UA_REMOVE_DESIGN_FROM_SHOP = "//select[@id='bulk_action']/option[contains(@value, 'delete_from_shop')]";
  public final static String UA_ADD_DESIGN_TO_SHOP = "//select[@id='bulk_action']/option[contains(@value, 'add_to_shop')]";
  public final static String UA_ADD_DESIGN_TO_CATEGORY = "//select[@id='bulk_action']/option[contains(@value, 'add_to_category')]";
  public final static String UA_CHANGE_DESIGN_POSITION = "//select[@id='bulk_action']/option[contains(@value, 'sort')]";
  public final static String UA_DESIGNS_SORT_DROP_DOWN = "//*[@id='bulk_sort']/select[contains(@id,'e_position')]";
  public final static String UA_DESIGNS_BULK_SORT_TOP = "//*[@id='bulk_sort']/select/option[contains(@value,'top')]";
  public final static String UA_DESIGNS_BULK_SORT_BOTTOM = "//*[@id='bulk_sort']/select/option[contains(@value,'bottom')]";
  public final static String UA_DESIGNS_BULK_SORT_NEXT_PAGE = "//*[@id='bulk_sort']/select/option[contains(@value,'next')]";
  public final static String UA_DESIGNS_BULK_SORT_PREV_PAGE = "//*[@id='bulk_sort']/select/option[contains(@value,'previous')]";

  public final static String UA_ID_MARKETPLACE_FOR_SHOPSELECT = "mp";
  public final static String UA_ID_SHOWALL_FOR_SHOPSELECT = "all";
  public final static String UA_VISIT_SHOP = "//*[@id='gotoShopLink']/a";

  /**
   * User area navigation & content
   */
  public final static String UA_DASHBOARD = "//div[@id='dashboard']";
  public final static String UA_DASHBOARD_QUICKLINKS = UA_DASHBOARD + "//div[@id='quicklinks']";

  public final static String UA_DASHBOARD_QUICKLINKS_CREATE_PRODUCT = UA_DASHBOARD_QUICKLINKS
      + "//*[@id='qlCreateProduct']";

  public final static String UA_DASHBOARD_QUICKLINKS_ORDERS_IN_SHOP = UA_DASHBOARD_QUICKLINKS
      + "//a[contains(., 'meinen Bestellungen im Shop')]";

  public final static String UA_DASHBOARD_QUICKLINKS_PUBLIC_PROFILE = UA_DASHBOARD_QUICKLINKS;

  public final static String UA_DESIGNS_UPLOAD_DESIGN_DATA = "//div[@id='content']"
      + "//div[@id='uploadDesigns']";
  public final static String UA_DESIGNS_UPLOAD_DESIGN_FORM = UA_DESIGNS_UPLOAD_DESIGN_DATA
      + "//form[@id='upload_design_form']//p";
  public final static String UA_DESIGNS_SORT_DESIGNS = "//div[@id='sidebar']" + "//a[@id='navi-4-2429']";
  public final static String UA_DESIGNS_SORT_DESIGNS_DATA = "//div[@id='content']"
      + "//div[@id='designsSort']";
  public final static String UA_DESIGNS_SORT_DESIGNS_FORM = UA_DESIGNS_SORT_DESIGNS_DATA
      + "//form[@id='frmDesignsSort']";
  public final static String UA_DESIGNS_DESIGN_CATEGORIES = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2414']";
  public final static String UA_DESIGNS_DESIGN_CATEGORIES_FORM = "//div[@id='content']"
      + "//form[@id='frmDesignCategories']//p";
  public final static String UA_DESIGNS_DESIGN_GALLERY_THEME_SEARCH = "//div[@id='sidebar']"
      + "//div[contains(.,'Themensuche')]";
  public final static String UA_DESIGNS_MY_DESIGNS_REMOVE_DESIGN_FROM_SHOP_DATA_FORM = "//div[@id='content']"
      + "//div[@id='designRemove']//form[@id='remove-design']";

  /**
   * User area navigation & content TODO: DAB_changed grouped by 2nd level navigation Products (aka: "Produkte")
   */
  public final static String UA_PRODUCTS_MY_PRODUCTS = "//div[@id='sidebar']" + "//a[@id='navi-4-2344']";
  public final static String UA_PRODUCTS_MY_PRODUCTS_DATA = "//div[@id='content']"
      + "//div[@id='products']";
  public final static String UA_PRODUCTS_MY_PRODUCTS_DATA_FORM = UA_PRODUCTS_MY_PRODUCTS_DATA
      + "//form[@id='frmProducts']";
  public final static String UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA = "//div[@id='content']"
      + "//div[@id='productDetails']";
  public final static String UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA
      + "//form[@id='frmProductsDetails']";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_ID_VALUE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/li[1]/div[1]/div[1]/p";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_NAME_LABEL = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "//*[@for='p_name']";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_DESCRIPTION_LABEL = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "//*[@for='p_description']";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_SEARCH_KEYWORDS_LABEL = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dt[position()=3]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_SEARCH_KEYWORDS_VALUE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd[position()=3]/textarea";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_CATEGORY_LABEL = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dt[position()=4]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_CATEGORY_VALUE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd[position()=4]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_BASEPRICE_VALUE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd[position()=6]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_COMMISSION_VALUE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd[position()=7]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_TOTALPRICE_LABEL = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dt[position()=10]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_TOTALPRICE_VALUE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd[position()=10]";
  public static final String UA_PRODUCTS_MY_PRODUCTS_DETAILS_STATUSLINK = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd//a[contains(@href,'updateSingle')]";
  public final static String UA_PRODUCTS_MY_PRODUCTS_DETAILS_PRODUCT_VIEWS = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "//*[@class='thumbimg-list product-thumbimg-list-details']";
  public final static String UA_PRODUCTS_MY_PRODUCTS_DETAILS_PRODUCT_COLORS_IMAGE = UA_PRODUCTS_MY_PRODUCTS_DETAILS_DATA_FORM
      + "/dl/dd[position()=13]/img[contains(@border,'1')]";

  public final static String UA_PRODUCTS_CREATE_PRODUCT = "//div[@id='sidebar']"
      + "//a[@id='navi-4-253']";
  public final static String UA_PRODUCTS_CREATE_PRODUCTS_DATA = "//div[@id='content']"
      + "//div[@id='confomatdiv']";
  public final static String UA_PRODUCTS_SORT_PRODUCTS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2428']";
  public final static String UA_PRODUCTS_SORT_PRODUCTS_DATA = "//div[@id='content']"
      + "//div[@id='productsSort']";
  public final static String UA_PRODUCTS_OWN_CATEGORIES = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2430']";
  public final static String UA_PRODUCTS_OWN_CATEGORIES_DATA = "//div[@id='content']"
      + "//div[@id='product-data']";
  public final static String UA_PRODUCTS_OWN_CATEGORIES_DATA_FORM = UA_PRODUCTS_OWN_CATEGORIES_DATA
      + "//form[@id='frmProductsGroups']";
  public final static String UA_PRODUCTS_GALLERY_PICTURES = "//div[@id='sidebar']"
      + "//a[@id='navi-4-24']";
  public final static String UA_PRODUCTS_GALLERY_PICTURES_DATA = "//div[@id='content']"
      + "//div[@id='gallery']";
  public final static String UA_PRODUCTS_DATA_EXPORT = "//div[@id='sidebar']" + "//a[@id='navi-4-2347']";
  public final static String UA_PRODUCTS_DATA_EXPORT_DATA = "//div[@id='content']"
      + "//div[@id='productsExport']";

  /**
   * User area navigation & content TODO: DAB_changed grouped by 2nd level navigation Shops
   */
  public final static String UA_SHOPS_SHOP_OVERVIEW = "//div[@id='sidebar']" + "//a[@id='navi-4-2295']";
  public final static String UA_SHOPS_SHOP_OVERVIEW_DATA_ADMIN = "//div[@id='content']"
      + "//div[@id='shopAdminOverview']";

  public final static String UA_SHOPS_INFO_META = "//div[@id='sidebar']" + "//a[@id='navi-4-2453']";
  public final static String UA_SHOPS_INFO_META_DATA = "//div[@id='content']"
      + "//div[@id='elementsGeneralShow']";
  public final static String UA_SHOPS_INFO_META_GENERAL_DATA = "//div[@id='content']"
      + "//div[@id='elementsGeneralEdit']";
  public final static String UA_SHOPS_INFO_META_GENERAL_DATA_EDIT_BUTTON = UA_SHOPS_INFO_META_GENERAL_DATA
      + "//input[@id='editGeneralElements']";
  public final static String UA_SHOPS_INFO_META_METADATA_DATA = "//div[@id='content']"
      + "//div[@id='metadata']";
  public final static String UA_SHOPS_INFO_META_METADATA_DATA_EDIT_BUTTON = UA_SHOPS_INFO_META_METADATA_DATA
      + "//input[@id='']";
  public final static String UA_SHOPS_INFO_META_METADATA_DATA_SHOW_SHOP_BUTTON = UA_SHOPS_INFO_META_METADATA_DATA
      + "//input[@id='']";
  public final static String UA_SHOPS_LEGAL_INFORMATION = "//div[@id='sidebar']"
      + "//a[@id='navi-4-3225']";
  public final static String UA_SHOPS_LEGAL_INFORMATION_DATA = "//div[@id='content']"
      + "//div[@id='addressData']";
  public final static String UA_SHOPS_LEGAL_INFORMATION_DATA_FORM = UA_SHOPS_LEGAL_INFORMATION_DATA
      + "//form[@id='address_form']";
  public final static String UA_SHOPS_LEGAL_INFORMATION_DATA_FORM_SAVE_BUTTON = UA_SHOPS_LEGAL_INFORMATION_DATA_FORM
      + "//input[@id='save_impress']";
  public final static String UA_SHOPS_LANGUAGE_CURRENCY = "//div[@id='sidebar']" + "//a[@id='navi-4-41']";
  public final static String UA_SHOPS_LANGUAGE_CURRENCY_DATA = "//div[@id='content']"
      + "//div[@id='langLocale']";
  public final static String UA_SHOPS_LANGUAGE_CURRENCY_DATA_LANGUAGE_FORM = UA_SHOPS_LANGUAGE_CURRENCY_DATA
      + "//form[@id='updateLang_form']";
  public final static String UA_SHOPS_LANGUAGE_CURRENCY_DATA_LANGUAGE_FORM_SAVE_BUTTON = UA_SHOPS_LANGUAGE_CURRENCY_DATA_LANGUAGE_FORM
      + "//*[@id='saveLanguage']";
  public final static String UA_SHOPS_LANGUAGE_CURRENCY_DATA_CURRENCY_FORM = UA_SHOPS_LANGUAGE_CURRENCY_DATA
      + "//form[@id='updateLocCurr_form']";
  public final static String UA_SHOPS_LANGUAGE_CURRENCY_DATA_CURRENCY_FORM_SAVE_BUTTON = UA_SHOPS_LANGUAGE_CURRENCY_DATA_CURRENCY_FORM
      + "//*[@id='saveCurrency']";
  public final static String UA_SHOPS_LINKING = "//div[@id='sidebar']" + "//a[@id='navi-4-573']";
  public final static String UA_SHOPS_LINKING_DATA = "//div[@id='content']" + "//div[@id='linking']";
  public final static String UA_SHOPS_ACCESS_PROTECTION = "//div[@id='sidebar']" + "//a[@id='navi-4-42']";
  public final static String UA_SHOPS_ACCESS_PROTECTION_DATA = "//div[@id='content']"
      + "//div[@id='shopRestrictions']";
  public final static String UA_SHOPS_ACCESS_PROTECTION_DATA_FORM = UA_SHOPS_ACCESS_PROTECTION_DATA
      + "//form[@id='shop-restrictions']";
  public final static String UA_SHOPS_ACCESS_PROTECTION_DATA_FORM_SAVE_BUTTON = UA_SHOPS_ACCESS_PROTECTION_DATA_FORM
      + "//input[@id='submit']";

  public final static String UA_SHOPS_APPEARANCE_COLORSFONTS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-37']";
  public final static String UA_SHOPS_APPEARANCE_COLORSFONTS_DATA = "//div[@id='content']"
      + "//div[@id='colorsFonts']";
  public final static String UA_SHOPS_APPEARANCE_HEADERFOOTER = "//div[@id='sidebar']"
      + "//a[@id='navi-4-39']";
  public final static String UA_SHOPS_APPEARANCE_HEADERFOOTER_DATA = "//div[@id='content']"
      + "//div[@id='headerFooter']";
  public final static String UA_SHOPS_APPEARANCE_CSS_DATA = "//div[@id='content']";
  public final static String UA_SHOPS_APPEARANCE_GRAPHICS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2519']";
  public final static String UA_SHOPS_APPEARANCE_GRAPHICS_DATA = "//div[@id='content']"
      + "//div[@id='shopFiles']";
  public final static String UA_SHOPS_APPEARANCE_INVOICELOGO = "//div[@id='sidebar']"
      + "//a[@id='navi-4-14']";
  public final static String UA_SHOPS_APPEARANCE_INVOICELOGO_DATA = "//div[@id='content']"
      + "//div[@id='elementsGeneralEdit']";

  public final static String UA_SHOPS_MARKETING_NEWSLETTER = "//div[@id='sidebar']"
      + "//a[@id='navi-4-4401']";
  public final static String UA_SHOPS_MARKETING_NEWSLETTER_DATA = "//div[@id='content']"
      + "//div[@id='shopNewsletter']";
  public final static String UA_SHOPS_MARKETING_NEWSLETTER_DATA_HISTORY = "//div[@id='content']"
      + "//div[@id='newsletterHistory']";

  public final static String UA_SHOPS_MARKETING_DISCOUNTS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2426']";
  public final static String UA_SHOPS_MARKETING_DISCOUNTS_DATA = "//div[@id='content']"
      + "//div[@id='pricediscount']";
  public final static String UA_SHOPS_MARKETING_ADWORDS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2413']";
  public final static String UA_SHOPS_MARKETING_SEOTUTORIAL = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2413']";
  public final static String UA_SHOPS_MARKETING_WEBANALYTICS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-6058']";
  public final static String UA_SHOPS_MARKETING_WEBANALYTICS_DATA = "//div[@id='content']"
      + "//div[@id='webanalytics']";
  public final static String UA_SHOPS_MARKETING_WEBANALYTICS_DATA_FORM = UA_SHOPS_MARKETING_WEBANALYTICS_DATA
      + "//form[@id='affiliate_programs']";

  public final static String UA_SHOPS_MARKETING_AFFILIATES = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2425']";
  public final static String UA_SHOPS_MARKETING_AFFILIATES_DATA = "//div[@id='content']"
      + "//div[@id='affiliateProgrammes']";
  public final static String UA_SHOPS_MARKETING_AFFILIATES_DATA_FORM = UA_SHOPS_MARKETING_AFFILIATES_DATA
      + "//form[@id='affiliate_programs']";
  public final static String UA_SHOPS_MARKETING_SPREADUCATION = "//div[@id='sidebar']"
      + "//a[@id='navi-4-5906']";

  // old .... figure where this comes from and if it is needed any longer
  public final static String UA_SHOPS_MARKETING = "//div[@id='sidebar']" + "//a[@id='navi-3-47']";
  public final static String UA_SHOPS_ANALYTICS = "//div[@id='sidebar']" + "//a[@id='navi-4-6058']";

  /**
   * User area navigation & content TODO: DAB_changed grouped by 2nd level navigation Statistics (aka: Statistiken)
   */
  public final static String UA_STATISTICS_CREDIT = "//div[@id='sidebar']" + "//a[@id='navi-4-241']";
  public final static String UA_STATISTICS_CREDIT_DATA = "//div[@id='content']"
      + "//div[@id='reportsFinancialsProfit']";
  public final static String UA_STATISTICS_DESIGNS_SOLD = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2293']";
  public final static String UA_STATISTICS_DESIGNS_SOLD_DATA = "//div[@id='content']"
      + "//div[@id='reportsShopsDesigns']";
  public final static String UA_STATISTICS_DESIGNS_SOLD_DATA_TIMEFORM = UA_STATISTICS_DESIGNS_SOLD_DATA
      + "//form[@id='timeForm']";
  public final static String UA_STATISTICS_PRODUCTS_SOLD = "//div[@id='sidebar']"
      + "//a[@id='navi-4-249']";
  public final static String UA_STATISTICS_PRODUCTS_SOLD_DATA = "//div[@id='content']"
      + "//div[@id='reportsShopsProducts']";
  public final static String UA_STATISTICS_PRODUCTS_SOLD_DATA_TIMEFORM = UA_STATISTICS_PRODUCTS_SOLD_DATA
      + "//form[@id='timeForm']";
  public final static String UA_STATISTICS_PRODUCT_RECORDS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-245']";
  // better to understand, does the same anyway ...
  public final static String UA_STATISTICS_MP_PRODUCT_STATISTICS = UA_STATISTICS_PRODUCT_RECORDS;
  public final static String UA_STATISTICS_PRODUCT_RECORDS_DATA = "//div[@id='content']"
      + "//div[@id='reportsMarketplaceProducts']";
  public final static String UA_STATISTICS_DESIGN_RECORDS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-244']";
  // better to understand, does the same anyway ...
  public final static String UA_STATISTICS_MP_DESIGN_STATISTICS = UA_STATISTICS_DESIGN_RECORDS;
  public final static String UA_STATISTICS_DESIGN_RECORDS_DATA = "//div[@id='content']"
      + "//div[@id='reportsMarketplacedesigns']";

  // TODO: OLD OLD OLD .... we should get rid of this ....
  public final static String UA_SHOP_STATISTICS = "//div[@id='sidebar']" + "//a[@id='navi-3-246']";
  public final static String UA_DATA_EXPORT = "//div[@id='sidebar']" + "//a[@id='navi-3-2347']";

  public final static String UA_SHOP_SOLD_PRODUCTS = "//div[@id='sidebar']" + "//a[@id='navi-4-249']";
  public final static String UA_SHOP_TOP_TEN = "//div[@id='sidebar']" + "//a[@id='navi-4-2294']";
  public final static String UA_CONVERSION = "//div[@id='sidebar']" + "//a[@id='navi-4-248']";
  public final static String UA_SHOP_REFERER = "//div[@id='sidebar']" + "//a[@id='navi-4-3520']";

  /**
   * User area navigation & content TODO: DAB_changed grouped by 2nd level navigation Account Settings(aka:
   * Kontoeinstellungen)
   */
  public final static String UA_ACCOUNT_SETTINGS_USER_DATA = "//div[@id='sidebar']"
      + "//a[@id='navi-4-162']";
  public final static String UA_ACCOUNT_SETTINGS_USER_DATA_ADDRESSFORM = "//div[@id='content']"
      + "//div[@id='tpl_address']//form[@id='addressForm']";
  public final static String UA_ACCOUNT_SETTINGS_ABOUT_ME = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2605']";
  public final static String UA_ACCOUNT_SETTINGS_ABOUT_ME_DATA = "//div[@id='content']"
      + "//div[@id='aboutMe']";
  public final static String UA_ACCOUNT_SETTINGS_ABOUT_ME_DATA_FORM = UA_ACCOUNT_SETTINGS_ABOUT_ME_DATA
      + "//form[@id='profileForm']";
  public final static String UA_ACCOUNT_SETTINGS_MY_ORDER = "//div[@id='sidebar']"
      + "//a[@id='navi-4-3472']";
  public final static String UA_ACCOUNT_SETTINGS_MY_ORDER_DATA = "//div[@id='content']"
      + "//div[@id='myOrder']";
  public final static String UA_ACCOUNT_SETTINGS_FINANCE = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2427']";
  public final static String UA_ACCOUNT_SETTINGS_FINANCE_DATA = "//div[@id='content']"
      + "//div[@id='bankdataOverview']";
  public final static String UA_ACCOUNT_SETTINGS_FINANCE_DATA_BANKDATA = "//div[@id='content']"
      + "//div[@id='bankdataAccount']";
  public final static String UA_ACCOUNT_SETTINGS_FINANCE_DATA_BANKDATA_FORM = UA_ACCOUNT_SETTINGS_FINANCE_DATA_BANKDATA
      + "//form[@id='bankForm']";
  public final static String UA_ACCOUNT_SETTINGS_BUTTON_TAX_DATA = "//div[@id='content']"
      + "//*[@id='btnTaxEdit']";
  public final static String UA_ACCOUNT_SETTINGS_FINANCE_DATA_PAYPAL = "//div[@id='content']"
      + "//div[@id='bankdataPaypal']";
  public final static String UA_ACCOUNT_SETTINGS_FINANCE_DATA_PAYPAL_FORM = UA_ACCOUNT_SETTINGS_FINANCE_DATA_PAYPAL
      + "//form[@id='bankForm']";
  public final static String UA_ACCOUNT_SETTINGS_MY_FAVORITES = "//div[@id='sidebar']"
      + "//a[@id='navi-4-252']";
  public final static String UA_ACCOUNT_SETTINGS_MY_FAVORITES_DATA = "//div[@id='content']"
      + "//div[@id='tpl_favoriteProducts']";
  public final static String UA_ACCOUNT_SETTINGS_NEWSLETTER_SUBSCRIPTIONS = "//div[@id='sidebar']"
      + "//a[@id='navi-4-26']";
  public final static String UA_ACCOUNT_SETTINGS_NEWSLETTER_SUBSCRIPTIONS_DATA = "//div[@id='content']"
      + "//div[@id='subscriptions']";
  public final static String UA_ACCOUNT_SETTINGS_LOGIN_INFO = "//div[@id='sidebar']"
      + "//a[@id='navi-4-2579']";
  public final static String UA_ACCOUNT_SETTINGS_LOGIN_INFO_DATA = "//div[@id='content']"
      + "//div[@id='passwordlogin']";
  public final static String UA_ACCOUNT_SETTINGS_BECOME_PREMIUM_PARTNER = "//div[@id='sidebar']"
      + "//a[@id='navi-4-164']";
  public final static String UA_ACCOUNT_SETTINGS_BECOME_PREMIUM_PARTNER_FORM = "//div[@id='content']"
      + "//form[@id='cms_upgradePremium']";
  public final static String UA_ACCOUNT_SETTINGS_DELETE_ACCOUNT = "//div[@id='sidebar']"
      + "//a[@id='navi-4-166']";
  public final static String UA_ACCOUNT_SETTINGS_DELETE_ACCOUNT_FORM = "//div[@id='content']"
      + "//div[@id='delete']//form[@id='deleteForm']";
  public final static String UA_ACCOUNT_SETTINGS_SAVE = "//*[@title='Speichern']";

  /**
   * User area navigation & content TODO: DAB_changed grouped by 2nd level navigation News
   */
  // where does all of that come from????
  //
  // public final static String UA_SHOPS_NEWSLETTER = "navi-4-4398";
  // public final static String UA_NEWS_SHOPS_LINKING = "navi-3-49";
  // public final static String UA_SHOP_LOCALE_SETTINGS = "navi-4-41";

  public final static String UA_PROFILE_I_AM = "navi-3-4";
  // public final static String UA_FAVORITE_PRODUCTS = "navi-3-15";
  public final static String UA_IMAGE_GALLERY = "navi-3-24";
  public final static String UA_SUBSCRIPTIONS = "navi-3-25";
  public final static String UA_FAVORITES = "navi-3-15";
  public final static String UA_UPLOAD_DESIGN = "navi-3-2341";
  public final static String UA_SHOP_ELEMENTS = "navi-4-2522";
  public final static String UA_SHOP_IMPRESSUM = "navi-4-3225";
  public final static String UA_SHOP_TITLE_METADATA = "navi-4-44";
  public final static String UA_SHOP_LANG_CURRENCY = "navi-4-41";
  public final static String UA_SHOP_ACCESS_PROTECTION = "navi-4-42";
  public final static String UA_SHOP_LAYOUT = "navi-3-35";
  public final static String UA_SHOP_HEADER_FOOTER = "navi-4-39";
  public final static String UA_SHOP_CSS_SETTINGS = "navi-4-36";
  public final static String UA_SHOP_GRAFICS = "navi-4-2519";
  public final static String UA_SHOP_CSS_COLOUR = "navi-5-3550";
  public final static String UA_SHOP_CSS_STRUCTURE = "navi-5-3551";
  public final static String UA_SHOP_CSS_CONFOMAT = "navi-5-3552";
  public final static String UA_SHOP_LINKING = "navi-3-49";
  public final static String UA_SHOP_PARTNER_PROGRAM = "navi-4-2425";
  public final static String UA_SHOP_DISCOUNTS = "navi-4-2426";
  public final static String UA_SHOP_GOOGLE_ANALYTICS = "navi-4-3519";
  public final static String UA_NEWS = "navi-2-3830";
  public final static String UA_NEWSLETTER_ARCHIVE = "navi-2-3830";

  /**
   * Subnavigation in userarea
   */
  public final static String UA_DESIGNS_GALLERY_CATEGORIES = "//ul[@id='navi-level-4']/li";

  /**
   * Checkout
   */
  public final static String CHECKOUT_NAV_STEP_1 = "//div[@id='steps']//li[@id='wizz-step-1']";
  public final static String CHECKOUT_NAV_STEP_2 = "//div[@id='steps']//li[@id='wizz-step-2']";
  public final static String CHECKOUT_NAV_STEP_3 = "//div[@id='steps']//li[@id='wizz-step-3']";
  public final static String CHECKOUT_NAV_STEP_4 = "//div[@id='steps']//li[@id='wizz-step-4']";
  // use in combination with above
  public final static String CHECKOUT_NAV_STEP_ACTIVE = "[contains(@class,'current')]";
  public final static String CHECKOUT_NAV_STEP_DONE = "[contains(@class,'done')]";

  // are working on marketplace AND shops but NOT for company
  public final static String CHECKOUT_PLATFORM_STEP_1 = "//li[contains(@id,'step-1')]";
  public final static String CHECKOUT_PLATFORM_STEP_2 = "//li[contains(@id,'step-2')]";
  public final static String CHECKOUT_PLATFORM_STEP_3 = "//li[contains(@id,'step-3')]";
  public final static String CHECKOUT_PLATFORM_STEP_4 = "//li[contains(@id,'step-4')]";

  // are working on marketplace AND shops but NOT for company
  public final static String CHECKOUT_company_STEP_1 = "//li[contains(@id,'menuCheckoutBasket')]";
  public final static String CHECKOUT_company_STEP_2 = "//li[contains(@id,'menuCheckoutAddress')]";
  public final static String CHECKOUT_company_STEP_3 = "//li[contains(@id,'menuCheckoutPayment')]";
  public final static String CHECKOUT_company_STEP_4 = "//li[contains(@id,'menuCheckoutOrder')]";

  public final static String CHECKOUT_MESSAGE_VAT_FREE_DELIVERY = "//div[preceding-sibling::div[contains(@class,'address-wrapper')]][child::p[@class='system-message']]";
  public final static String CHECKOUT_MESSAGE_VAT_CHECK_ERROR = "//div[@id='checkoutAddress']/*[contains(@class,'system-error')]";

  /** Basket & Coupon **/
  public final static String GO_TO_CHECKOUT = "//*[@value='Zur Kasse gehen ']";
  public final static String REDEEM_COUPON = "link=Gutschein einlösen";
  public final static String REMOVE_COUPPON = "link=Gutschein entfernen";
  public final static String COUPON_CODE_FIELD = "//*[@id='couponCode']";
  public final static String BASKET_TOTAL_PRICE = "//*[@id='basketPriceTotal']/div[2]//strong[@class='price-higlihted']";
  public final static String ARTICLE_PRICE = "//*[@class='basketPriceTotal']//span";
  public final static String SHIPPING_PRICE = "//*[@id='basketShippingPrice']//span//strong";
  public final static String COUPON_SHIPPING_REDUCTION = "//*[@id='basketCouponShippingReduction']//span";
  public final static String SHIPPING_REDUCTION_WITH_ARTICLE = "//*[@id='basketCouponShippingReduction']//span//strong";
  public final static String ARTICLE_REDUCTION = "//*[@id='basketCoupon']//span";
  public final static String CONTINUE_SHOPPING_BOTTOM = "//*[@id='continueShoppingButtonBottom']";
  /** Checkout - Address Information Page **/
  public final static String CHECKOUT_NEW_USER_HEADING = "//*[@class='checkoutUserRegister border_top_color_1']/h2";
  public final static String GO_TO_PREV_ADDRESSES = "link=Adressvorlage verwenden";
  public final static String SELECT_DELIVERY_ADDRESS = "link=abweichende Lieferadresse eingeben";
  public final static String GIFT_WRAP = "//fieldset[@id='checkoutGift']/div[1]";
  public final static String GIFT_TEXT = "//*[@id='giftText']";
  public final static String EMAIL_REGISTRATION_FIELD = "//fieldset[@id='registerMail']//input";
  public final static String MP_EMAIL_REGISTRATION_ERROR = "//*[@id='checkoutAddress']/p[2]";
  public final static String SHOP_EMAIL_REGISTRATION_ERROR = "//*[@id='checkoutAddress']/p";
  public final static String GO_TO_PAYMENT_METHOD = "//input[@value='Weiter zur Zahlungsart']";
  public final static String SELECT_AS_BILLING_ADDRESS = "//*[@id='basketAddressSelect']/fieldset[2]/ul/li[1]/a";
  public final static String LOGIN_IN_CHECKOUT = "user-login-link-button";
  public final static String SET_FIRST_TEMPLATE_AS_BILLING_ADDRESS = "//*[@id='basketAddressSelect']/fieldset[1]/ul/li[1]/a";
  public final static String FORGOT_PASSWORD_LINK = "link=exact:Du hast Dein Passwort oder Deinen Benutzernamen vergessen?";
  public final static String FIRST_TEMPLATE_STREET = "//*[@id='basketAddressSelect']/fieldset[1]/div[4]/span";
  public final static String SECOND_TEMPLATE_STREET = "//*[@id='basketAddressSelect']/fieldset[2]/div[4]/span";
  public final static String FIRST_TEMPLATE_CITY = "//*[@id='basketAddressSelect']/fieldset[1]/div[6]/span";
  public final static String SECOND_TEMPLATE_CITY = "//*[@id='basketAddressSelect']/fieldset[2]/div[6]/span";

  /** Shipping types **/
  public final static String DELIVERY_BY_EMAIL_LABEL = "//*[@id='ShippingDeliveryType12']/label";
  public final static String STANDARD_DELIVERY_LABEL = "//*[@id='ShippingDeliveryType1']/label";
  public final static String EXPRESS_DELIVERY_LABEL = "//*[@id='ShippingDeliveryType2']/label";
  public final static String DELIVERY_ADDRESS_CHECKBOX = "//input[@id='shippingAddressCheckbox']";
  public final static String PACKSTATION_CHECKBOX = "//input[@id='shippingAddressPsCheckbox']";
  public final static String EMAIL_ADDRESS_FOR_DELIVERY = "//*[@id='ShippingDeliveryExplanation12']/small";
  public final static String STANDARD_DELIVERY_PRICE = "//*[@id='ShippingDeliveryType1']/span";
  public final static String STANDARD_DELIVERY_TEXT = "//*[@id='ShippingDeliveryExplanation1']/small";
  public final static String EXPRESS_DELIVERY_PRICE = "//*[@id='ShippingDeliveryType2']/span";
  public final static String EXPRESS_DELIVERY_TEXT = "//*[@id='ShippingDeliveryExplanation2']/small/strong";
  public final static String EXPRESS_DELIVERY_DATE_TEXT = "//*[@id='ShippingDeliveryDateSpan2']//a";
  public final static String DELIVERY_ADDRESS_FIELDS = "//*[@id='checkoutDeliveryAddress']/fieldset";
  public final static String PACKSTATION_FIELDS = "//*[@id='checkoutDeliveryPackstation']/div[1]/p[2]";

  /** Checkout - Payment Method Page **/
  public final static String PLACE_ORDER = "//*[@value='Bestellung abschicken']";
  public final static String MP_PAYMENT_PAGE_TITLE = "//*[@id='checkoutPaymentInfo']/h1";
  public final static String SHOP_PAYMENT_PAGE_TITLE = "//*[@class='checkoutInformationBox']/h1";
  public final static String PAYMENT_METHOD_FORM = "xpath=id('frmPaymentInfo')//a";
  public final static String CONFIRM_ORDER_DETAILS = "//*[@value='Bestelldaten prüfen']";
  public final static String CONFIRM_PAYMENT = "//*[@class='checkoutStepsBottom']//input";
  public final static String EXPRESS_DELIVERY_MSG_PAYMENT_PAGE = "//fieldset[@id='paymentMethodContent3']/p[3]";

  /** Checkout - Confirm Order Details Page **/
  public final static String ORDER_ADDRESS_FORM = "//*[@id='frmCheckoutAddress']//a";
  public final static String CHANGE_BILLING_ADDRESS = "//*[@id='checkBillingAddress']//a";
  public final static String CHANGE_DELIVERY_ADDRESS = "//*[@id='checkDeliveryAddress']//a";
  public final static String CHANGE_PAYMENT_METHOD = "xpath=//*[@id='checkPayment']//a";
  public final static String CHANGE_GIFT_TEXT = "//*[@id='checkGift']//a";
  public final static String SHIPPING_PRICE_CONFIRMATION_PAGE = "//*[@id='basketShippingPrice']//span";
  public final static String SHIPPING_REDUCTION_CONFIRMATION_PAGE = "//*[@id='basketCouponShipping']//span";
  public final static String BASKET_TOTAL_CONFIRMATION_PAGE = "//*[@id='basketPriceTotal']//span";
  public final static String ARTICLE_REDUCTION_CONFIRMATION_PAGE = "//*[@id='basketCoupon']//span";
  public final static String ARTICLE_PRICE_CONFIRMATION_PAGE = "//*[@id='basketItems']/fieldset[1]/ul/li[8]/span";
  public final static String EXPRESS_DELIVERY_MSG_CONFIRMATION_PAGE = "//*[@id='expressOrder']/p";
  public final static String EXPRESS_DELIVERY_DATE_CONFIRMATION_PAGE = "//*[@id='expressOrder'][2]/p";

  public final static String PARTNER_PROMO_IMAGE = "//*[@class='create-own-shop-image']";
  public final static String PARTNER_PROMO_DESCRIPTION = "//*[@class='shop-partner-promo-box-description']";
  public final static String PARTNER_PROMO_TO_UA_BUTTON = "//*[@value='Weitere Informationen']";

  public final static String BACKEND_LOGOUT = "link=Logout";
  public final static String BACKEND_ADMINS_LIST = "//a[contains(@href, '4dm1n.php?op=admins_show')]";
  public final static String BACKEND_BACKLOG = "//a[contains(@href, '4dm1n.php?op=fulfillment_backlog')]";
  public final static String BACKEND_ORDERS = "//a[contains(@href, '4dm1n.php?op=transactions_show')]";
  public final static String BACKEND_ORDERS_EDIT = "/4dm1n.php?op=transaction_edit&transaction_id=";
  public final static String BACKEND_COMPLAINT_CREATE = "/4dm1n.php?op=complaint_edit&ac=new&transaction_id=";
  public final static String BACKEND_ORDERITEM_MOD = "/4dm1n.php?op=transaction_refund_modifications&transaction_id=";
  public final static String BACKEND_ORDERS_EDIT_BACKDOOR = "//*[contains(@href,'4dm1n.php?op=backdoor&page=partnerarea&partner_id=')]";
  public final static String BACKEND_ORDERS_PROCESS = "//a[contains(@href, '4dm1n.php?op=transactions_process&transaction_id=";
  public final static String BACKEND_COMPLAINTS = "/4dm1n.php?op=complaints_show";
  public final static String BACKEND_COMPLAINTS_DETAILS = "//*[contains(@name, 'details')]";
  public final static String BACKEND_COMPLAINTS_SOLUTION = "//*[contains(@name, 'solutions')]";
  public final static String BACKEND_GRAPHIC_SERVICE = "link=Graphic Service";
  public final static String BACKEND_CUSTOMERS_LIST = "//*[contains(@href, '4dm1n.php?op=customers_show')]";
  public final static String BACKEND_COUPON_CODE = "//*[contains(@href,'coupon_id')]";
  public final static String BACKEND_USER_OPT_OUT_IMG = "//img[@alt='User is opt-out']";
  public final static String BACKEND_SEARCH_BUTTON = "//input[@type='image']";
  public final static String BACKEND_EDIT_BUTTON = "//img[@alt='Edit']";
  public final static String BACKEND_ADDRESSES_LIST = "//form[@id='addresses_show']";
  public final static String BACKEND_PARTNER_SHOPS = "//img[@alt='Shops']";
  public final static String BACKEND_GO_TO_USERAREA = "//img[@alt='Shop Partner']";
  public final static String BACKEND_SYS_MAINTENANCE = "//a[contains(@href, '4dm1n.php?op=sys_maintenance_show')]";
  public final static String BACKEND_MAINTENANCE_SCRIPT_SELECT = "//select[@name='maintenance_script']";
  public final static String BACKEND_SCRIPT_PASSWORD = "//input[@name='password']";
  public final static String BACKEND_SCRIPT_START = "//input[@value='Go']";
  public final static String BACKEND_TO_PARTNER_AREA = "//img[@alt='Partner Area']/../../a";
  public final static String BACKEND_EDIT_STOCKOUT = "//*[@id='table_body']/tr/td/a/@href";
  public final static String BACKEND_STOCKOUT_CHECKBOX = "//input[@name='stockout']";
  public final static String BACKEND_STOCKOUT_SAVE = "//img[@title='save']";
  public final static String BACKEND_ORDER_STATUS = "table_data.2.5";
  public static final String BACKEND_TABLE_ROW = "//*[@class='tabdark']";
  public final static String BACKEND_EDIT_FIRST_ORDER = BACKEND_TABLE_ROW + "/a[contains(@target,'_blank')]@href";
  public final static String BACKEND_TRANSACTION_ERROR_REDOS = "4dm1n.php?op=transaction_errors_show&transaction_id=";
  public final static String BACKEND_ORDERDETAIL_HISTORY = "//*[@name = 'status']/..";
  public final static String BACKEND_ORDERDETAIL_ITEMHISTORY = "//tr[3]/td[8]";
  public final static String BACKEND_ORDERS_LIST = "//tbody[@id='table_body']";
  public final static String BACKEND_ERROR_MGMT_QUANTITY_FIRST_ITEM = "//input[contains(@name, 'quantity')]";
  public final static String BACKEND_TRANSACTION_COMPLAINTS = "4dm1n.php?op=complaints_show&transaction_id=";
  public final static String BACKEND_SORT_BY_DESIGNS_ID = "//*[@id='header_design_id']";
  public final static String BACKEND_FIRST_DESIGN = "//*[@class='tabdark']@id";
  public final static String BACKEND_PREMIUM_EXPIRATION_MONTH = "//*[@name='date[M]']/option[contains(@selected,'selected')]@value";
  public final static String BACKEND_PREMIUM_EXPIRATION_DAY = "//*[@name='date[d]']/option[contains(@selected,'selected')]@value";
  public final static String BACKEND_PREMIUM_EXPIRATION_YEAR = "//*[@name='date[Y]']/option[contains(@selected,'selected')]@value";
  public final static String BACKEND_DYNAMIC_CONTENT_LIST = "//table[2]";
  public final static String BACKEND_STATIC_CONTENT_LIST = "//form[@id='translation_search']/table";
  public final static String BACKEND_RETURN_TO_OVERVIEW = "//img[@alt='Zurück zur Übersicht']";
  public final static String BACKEND_SAVE_BUTTON = "//input[@value='Save']";
  public final static String BACKEND_GO_BUTTON = "//input[@value='GO']";
  public final static String BACKEND_PRODTYPE_TRANSLATION_NAME = "//input[@name='pt_name']";
  public final static String BACKEND_PRODTYPE_TRANSLATION_DESC_SHORT = "//*[@name='pt_description_short']";
  public final static String BACKEND_PRODTYPE_TRANSLATION_DESC_LONG = "//*[@name='pt_description']";
  public final static String BACKEND_PRODTYPE_INCLUDED_PRINTTYPES = "//input[@name='printtype_id[]']";
  public final static String BACKEND_PRODTYPE_ALL_PRINTTYPES = "//select[@name='printtype_id[]']";
  public final static String BACKEND_EXCLUDE_BUTTON = "//input[@value='Exclude']";
  public final static String BACKEND_INCLUDE_BUTTON = "//input[@value='Include']";
  public final static String BACKEND_LEGAL_STATUS_CELL = "//td[contains(@name,'status_value')]";
  public final static String BACKEND_LEGAL_REJECTED_COLOR = "//td[contains(@style,'(255, 85, 85)')]";
  public final static String BACKEND_LEGAL_OK_COLOR = "//td[contains(@style,'(255, 255, 0)')]";
  public final static String BACKEND_LEGAL_ONLINE_COLOR = "//td[contains(@style,'(85, 255, 85)')]";
  public final static String BACKEND_NEW_DOCUMENT = "//img[contains(@src,'Public/Admin/Icons/22/document-new.png')]";
  public final static String BACKEND_MARKETPLACE_DESIGNS = "//li[3]/ul/li[4]/ul/li[1]/a";
  public final static String BACKEND_SKU_TABLE_ROW = "//*[@id='table_body']/tr";
  public final static String BACKEND_SKU_NAME_CELL = "//td[contains(@name, 'object_name')]";
  public final static String BACKEND_SKU_ID_CELL = "//td[contains(@name, 'object_id')]";
  public final static String BACKEND_SKU_UNITS_CELL = "//td[contains(@name, 'units')]";
  public final static String BACKEND_SKU_UNITS_IN = "//*[@name='in']";
  public final static String BACKEND_ARTICLES_ID_NAME = "//td[contains(@name, 'article')]";
  public final static String BACKEND_PRODUCT_ID = "//td[contains(@name, 'product_id')]";
  public final static String BACKEND_ERROR = "//div[@class='error']";
  public final static String BACKEND_ARTICLE_FAMILY_ROW = "//*[@id='familyTable']//tr[contains(@id,'family')]";
  public final static String BACKEND_ARTICLE_FAMILY_SUBMISSION = "//a[contains(@href,'submission_id')]";
  public final static String BACKEND_CONTESTS_LINK = "company Contests";
  public final static String BACKEND_SMUS_PREFERENCES = "//*[@id='row_7']/td[5]/a/img";
  public final static String BACKEND_FINANCE_VATCHECK = "//a[contains(@href, '4dm1n.php?op=vat_check')]";
  public final static String BACKEND_INVENTORY_VAT_EXCEPTIONS = "//a[contains(@href, '4dm1n.php?op=vat_exceptions')]";
  public final static String BACKEND_EMPLOYEE_SHOP = "/4dm1n.php?op=employee_shop";

  /**
   * Product assortment
   */
  public final static String ASSORTMENT_NAVIGATION = "//*[@class='navi-level-4-header' and contains(.,'Produktsortiment')]";
  public final static String ASSORTMENT_DEPARTMENTS_OVERVIEW = "//*[@id='navi-level-4']/";
  public final static String ASSORTMENT_PRODUCTTYPE_TITLE = "//*[@id='www-content']/*[@class='main']/*[@class='section']";
  public final static String ASSORTMENT_DEPARTMENT = "//*[@class='section']/h1";
  public final static String ASSORTMENT_PRODUCTS_PATH = "//*[@class='triple-col-box medium-gap-down']";
  public final static String ASSORTMENT_DETAIL_DESCRIPTION = "/*[@class='dl-product-description']";

  /**
   * company filter contents
   */
  public static final String SIZE_XS = "1";
  public static final String SIZE_S = "2";
  public static final String SIZE_M = "3";
  public static final String SIZE_L = "4";
  public static final String SIZE_XL = "5";
  public static final String SIZE_XXL = "6";
  public static final String LF_ALL_SIZE_IDS[] = { SIZE_S, SIZE_M, SIZE_L, SIZE_XL, SIZE_XXL };
  public static final String LF_ALL_SIZE_NAMES[] = { "S", "M", "L", "XL", "XXL" };
  public static final String LF_MALE = "M";
  public static final String LF_FEMALE = "F";
  public static final String LF_COLOR_WHITE = "1";
  public static final String LF_COLOR_GREY = "2";
  public static final String LF_COLOR_BLACK = "3";
  public static final String LF_COLOR_YELLOW = "4";
  public static final String LF_COLOR_RED = "5";
  public static final String LF_COLOR_PINK = "6";
  public static final String LF_COLOR_PURPLE = "7";
  public static final String LF_COLOR_BLUE = "8";
  public static final String LF_COLOR_GREEN = "9";
  public static final String LF_COLOR_BROWN = "10";
  public static final String LF_ALL_COLOR_NAMES[] = { "white", "grey", "black", "yellow", "red", "pink", "purple",
    "blue", "green", "brown" };
  public static final String LF_ALL_COLORS[] = { LF_COLOR_WHITE, LF_COLOR_GREY, LF_COLOR_BLACK, LF_COLOR_YELLOW,
    LF_COLOR_RED, LF_COLOR_PINK, LF_COLOR_PURPLE, LF_COLOR_BLUE, LF_COLOR_GREEN, LF_COLOR_BROWN };
  public static final String LF_SUBMISSIONS_CURRENT = "//a[contains(@href,'/Contest/removefilter')]";
  public static final String LF_SUBMISSIONS_USERNEW = "//a[contains(@href,'/filter/new')]";
  public static final String LF_SUBMISSIONS_OLD = "//a[contains(@href,'/filter/accepted')]";
  public static final String LF_SUBMISSIONS_PRINTED = "//a[contains(@href,'/filter/sale')]";
  public static final String LF_SUBMISSION_LISTING_LABELS[] = { "aktuell", "neu für mich", "älter", "gedruckt" };
  public static final String LF_CONTESTID_CLASSIC = "1";
  public static final String LF_CONTESTNAME_CLASSIC = "Classics";

  /**
   * company shop overview
   */
  public static final String LF_CONTESTS = "//*[@id='contestLink']//a";
  public static final String LF_MAIN_SIZE_DROPDOWN = "dropdownSize";
  public static final String LF_SELECTED_SIZE = "//*[@id='dropdownSize']//*[contains(@class, 'selected')]";
  public static final String LF_COLOR_DROPDOWN = "//*[@id='dropdownColor']";
  public static final String LF_SELECTED_COLOR = "//*[contains(@class, 'selected')]/*[contains(@id, 'dropdownColorItem')]";
  public static final String LF_SHOPITEM = "//*[@class='shopitem']";
  public static final String LF_RETURN_TO_SHOP = "//*[@class='upShop']/a";
  public static final String LF_SHOPITEM_SELECTED_SIZE = "//a[contains(@id,'selectedSize')]";
  public static final String LF_SHOPITEM_ORDERBUTTON = "//a[@class='add_item']";
  public static final String LF_VIEWMODE_ALL = "//div[@class='showAll']";
  /** Login field */
  public static final String LF_OPEN_LOGIN_SLIDER = "//*[@id='login_slider_button']/a";
  public static final String LF_MINI_EMAIL = "email";
  public static final String LF_MINI_PWD = "password";
  public static final String LF_SAVE_LOGIN = "stay_logged";
  public static final String LF_LOGIN_BUTTON = "login_btn";
  public static final String LF_USERNAME_TOP = "topUser";

  /**
   * company Contest Overview
   */
  public static final String LF_CONTEST_FILTER = "//*[@id='menuContests']";
  public static final String LF_SELECTED_CONTEST = "//*[contains(@class, 'selected')]/a[contains(@id, 'menuContestsItem')]";
  public static final String LF_LISTINGS_FILTER = "//*[@id='dropdownSubmissions']";
  public static final String LF_SELECTED_LISTING = "//*[@id='dropdownSubmissionsSelected']";
  public static final String LF_CONTEST_SUBMISSION = "//*[@class='submission']";
  public static final String LF_RETURN_TO_CONTEST = "//*[@class='upContest']/a";
  /**
   * company Checkout
   */
  public static final String LF_CONTINUE_SHOPPING_BUTTON = "continueShoppingButtonTop";
  public static final String LF_ORDER_CONFIRMATION = "//*[contains(@class,'orderConfirmation')]";

  public static String getLFFamilyDetailsLink(final String familyId) {
    return "//a[contains(@href,'Article/index/id/" + familyId + "')]";
  }

  public static String getAssortmentProdTypeLink(final String producttypeID) {
    return "//a[contains(@href, 'productTypeId/" + producttypeID + "/type/detail')]";
  }

  public static String getAddToBasketLinkForFamily(final String familyId) {
    return "//form[@id='addToBasket" + familyId + "']//a[@class='add_item']";
  }

  public static String getContestCellFromSubmissionList(final String submissionId) {
    return "//td[contains(text(),'" + submissionId + "')]/../td[6]";
  }

  /**
   * company submission
   */
  public static final String SUBMISSION_RATING = "//*[@id='submissionStatistics']/span[2]/span/strong";
  public static final String SUBMISSION_TITLE = "//*[@id='submissionTitle']";
  public static final String SUBMISSION_COMMENT_COUNT = "//*[@class='commentCount']";
  public static final String SUBMISSION_COMMENT = "//*[@class='comment']";
  public static final String SUBMISSION_COMMENT_TEXT = "//*[@class='bubble clearfix']/p";
  public static final String SUBMISSION_PREORDER_COUNT = "//*[@id='submissionStatistics']/span[5]/span";
  public final static String SUBMISSION_BLOG_AREA = "//*[@id='blogthis']//textarea";
  public static final String SUBMISSION_COMMENT_AUTHOR = "//*[@class='author']/a";


  public static final String COMMENT_COUNT_PROFILE = "//*[@class='profileComments']/../*[@class='data']";
  public static final String VOTES_COUNT_PROFILE = "//*[@class='profileVotes']/../*[@class='data']";
  public static final String AFFILIATE_LINK = "//*[@id='userAffiliate']/p[2]/a";
}
