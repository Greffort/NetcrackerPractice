package com.greffort.java_se.main.main007;

import com.greffort.java_se.buildings.office.Office;
import com.greffort.java_se.buildings.office.OfficeFloor;
import com.greffort.java_se.buildings.threads.Cleaner;
import com.greffort.java_se.buildings.threads.Repairer;
import com.greffort.java_se.interfaces.Floor;
import com.greffort.java_se.interfaces.Space;

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
