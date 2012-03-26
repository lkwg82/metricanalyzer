/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.frontend;

import biz.company.qa.pageobjects.IValidatableObject;

/**
 * just a marker interface to indicate, that is has a navgation which sits at the top
 * 
 
 */
public interface HasTopNavigation<T extends IValidatableObject> {

    T getTopNavigation();
}