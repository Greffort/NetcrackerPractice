package com.greffort.main.main007;

import com.greffort.buildings.office.Office;
import com.greffort.buildings.office.OfficeFloor;
import com.greffort.buildings.threads.Cleaner;
import com.greffort.buildings.threads.Repairer;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;

/**
 * @author Main0701
 */
public class Main0701 {

    public static void main(String[] args) {
        Floor spaces001 = new OfficeFloor(new Space[]{new Office(21, 21),
                new Office(22, 22), new Office(23, 23),
                new Office(22, 22), new Office(23, 23),
                new Office(22, 22), new Office(23, 23)});


        Repairer repairer = new Repairer(spaces001);
        Cleaner cleaner = new Cleaner(spaces001);

//        repairer.setPriority(repairer.MAX_PRIORITY);
//        cleaner.setPriority(cleaner.MAX_PRIORITY);

        repairer.start();
//        repairer.interrupt();
//        System.out.println(repairer.getState());
        cleaner.start();
//        cleaner.interrupt();
    }
}
