package main.Entity;

import java.util.List;

public class Owner extends User{
    private String businessName;
    private List<String> colaborator;

    public Owner(String cnie, String firstName, String lastName, String businessName, List<String> colaborator) {
        super(cnie, firstName, lastName);
        this.businessName = businessName;
        this.colaborator = colaborator;
    }
}
