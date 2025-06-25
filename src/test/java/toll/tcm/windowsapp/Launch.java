package toll.tcm.windowsapp;

import java.io.File;
import java.util.Arrays;

import org.apache.maven.cli.MavenCli;
import org.apache.maven.shared.invoker.*;
public class Launch {

	Launch() {
		// TODO Auto-generated method stub
		String projectDirectory = System.getProperty("user.dir");

        // Specify the goals to execute (e.g., clean install)
        String[] goalsArray = {"clean", "install"};

        // Create an instance of MavenInvoker
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(projectDirectory, "pom.xml"));
        request.setGoals(Arrays.asList(goalsArray));

        Invoker invoker = new DefaultInvoker();

        // Run Maven
        try {
            InvocationResult result = invoker.execute(request);

            // Check the result
            if (result.getExitCode() == 0) {
                System.out.println("Maven build succeeded.");
            } else {
                System.err.println("Maven build failed. Exit code: " + result.getExitCode());
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }

}
