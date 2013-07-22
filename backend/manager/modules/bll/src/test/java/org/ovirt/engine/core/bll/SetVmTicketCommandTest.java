package org.ovirt.engine.core.bll;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.ovirt.engine.core.common.action.SetVmTicketParameters;
import org.ovirt.engine.core.common.users.VdcUser;

public class SetVmTicketCommandTest {
    // The command that will be tested:
    private SetVmTicketCommand<SetVmTicketParameters> command;

    @Before
    public void setUp() {
        command = new SetVmTicketCommand<>(new SetVmTicketParameters());
    }

    /**
     * Check that the constructed consule user name is {@code null} when the
     * user doesn't have a name.
     */
    @Test
    public void testNullConsoleUserNameWhenNoUserSet() {
        VdcUser user = new VdcUser();
        command.setCurrentUser(user);
        assertNull(command.getConsoleUserName());
    }

    /**
     * Check that when the user doesn't have a directory name the consule user
     * name contains only the login name.
     */
    @Test
    public void testOnlyLoginNameWhenNoDirectorySet() {
        VdcUser user = new VdcUser();
        user.setUserName("Legolas");
        user.setDomainControler("");
        command.setCurrentUser(user);
        assertEquals(command.getConsoleUserName(), "Legolas");
    }

    /**
     * Check that when the has a name and a directory name the console user name
     * is the user name followed by {@code @} and the directory name.
     */
    @Test
    public void testLoginNameAtDirectoryWhenDirectorySet() {
        VdcUser user = new VdcUser();
        user.setUserName("Legolas");
        user.setDomainControler("MiddleEarth.com");
        command.setCurrentUser(user);
        assertEquals(command.getConsoleUserName(), "Legolas@MiddleEarth.com");
    }
}
