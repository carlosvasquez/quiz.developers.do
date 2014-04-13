package org.devdom.quiz.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos Vasquez Polanco
 */
public final class Configuration {
    
    private Configuration(){ }
    
    public static final int ROLE_VIEWER = 1;
    public static final int ROLE_COMPETITOR = 2;
    public static final int ROLE_EDITOR = 3;
    public static final int ROLE_CREATOR = 4;
    public static final int ROLE_OWNER = 5;
    public static final int ROLE_ADMINISTRATOR = 6;

}