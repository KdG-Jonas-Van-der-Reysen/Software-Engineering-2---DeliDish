package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.common.ContactInfo;

public class ContactInfoFactory {
    public static ContactInfo create(Address address, String email, String tel) {
        return new ContactInfo(address, email, tel);
    }
}
