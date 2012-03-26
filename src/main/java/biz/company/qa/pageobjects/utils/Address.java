/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

/**
 * Class to create Address-Objects
 * 
 
 */
public class Address {
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Address [title=" + title + ", firstname=" + firstname + ", lastname=" + lastname + ", company="
                + company + ", street=" + street + ", street2=" + street2 + ", zipcode=" + zipcode + ", city=" + city
                + ", country=" + country + ", phonenumber=" + phonenumber + ", email=" + email + "]";
    }

    public final String MILLIS = String.valueOf(System.currentTimeMillis());
    public final static String TITLE_MR = "1";
    public final static String TITLE_MRS = "2";
    public final static String TITLE_MS = "3";
    public final static String TILE_COMPANY = "4";

    public final static String COUNTRY_DE = "DE";

    private String title;
    private String firstname;
    private String lastname;
    private String company;
    private String street;
    private String street2;
    private String zipcode;
    private String city;
    private String country;
    private String phonenumber;
    private String email;

    /**
     * Generate Adress with default values for example: title: 2 firstname: firstname126548946 lastname:
     * lastname54984613 ...
     */
    public Address() {
        this.title = TITLE_MR;
        this.firstname = "firstname" + MILLIS;
        this.lastname = "lastname" + MILLIS;
        this.company = "";
        this.street = "Street " + MILLIS;
        this.street2 = "Street2" + MILLIS;
        this.zipcode = MILLIS.substring(0, 4);
        this.city = "City" + MILLIS;
        this.country = COUNTRY_DE;
        this.phonenumber = MILLIS.substring(0, 7);
        this.email = MILLIS + "@qa-premium.de";
    }

    public static Address createAddressWithPrefix(String prefix) {
        Address address = new Address();
        address.setTitle(TITLE_MR);
        address.setFirstname(prefix + "firstname" + address.MILLIS);
        address.setLastname(prefix + "lastname" + address.MILLIS);
        address.setCompany(prefix + "");
        address.setStreet(prefix + "Street " + address.MILLIS);
        address.setStreet2(prefix + "Street2" + address.MILLIS);
        address.setZipcode(address.MILLIS.substring(0, 4));
        address.setCity(prefix + "City" + address.MILLIS);
        address.setCountry(COUNTRY_DE);
        address.setPhonenumber(address.MILLIS.substring(0, 7));
        address.setEmail(prefix + address.MILLIS + "@qa-premium.de");
        return address;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((phonenumber == null) ? 0 : phonenumber.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((street2 == null) ? 0 : street2.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Address))
            return false;
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (firstname == null) {
            if (other.firstname != null)
                return false;
        } else if (!firstname.equals(other.firstname))
            return false;
        if (lastname == null) {
            if (other.lastname != null)
                return false;
        } else if (!lastname.equals(other.lastname))
            return false;
        if (phonenumber == null) {
            if (other.phonenumber != null)
                return false;
        } else if (!phonenumber.equals(other.phonenumber))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (street2 == null) {
            if (other.street2 != null)
                return false;
        } else if (!street2.equals(other.street2))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (zipcode == null) {
            if (other.zipcode != null)
                return false;
        } else if (!zipcode.equals(other.zipcode))
            return false;
        return true;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * @param street2 the street2 to set
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
