/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import org.testng.annotations.Test;

/**
 
 */
public class DresdenTestAbstract {

    public void testReq2365() {
        /**
         * bright product: if product has a color for which no white underlay must be printed: classify design as design
         * type 'bright product' which colors are are bright with no underlay for the productType 6?
         */

        // "SELECT * FROM `ss_producttype_link_productcolor` p, `ss_producttype_colors` pc WHERE p.`producttype_id`=6 and p.active=1 and pc.type="bright" and p.`productcolor_id`=pc.`productcolor_id`""
    }

    @Test(enabled = false)
    public void testPDFContentWithExample() {
        // see DEV-17384
    }

    /**
     * <pre>
     * DEV-17085
     *    DEV-17223
     *    DEV-17222
     * </pre>
     */
    // @Test
    public void testMergedPrintTypes17und19() {

    }
}
