package net.xayanix.nssv.skyblock.skyuser.jobs;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {

    NONE("Brak"),
    GORNIK("Gornik"),
    DRWAL("Drwal"),
    //FARMER("Farmer"),
    LOWCA("Lowca");

    private String name;

}
