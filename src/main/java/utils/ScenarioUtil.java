package utils;

import helpers.TextFileHelper;
import io.cucumber.java.Scenario;

import java.io.File;
import java.util.List;

public class ScenarioUtil {
    private static final ThreadLocal<Scenario> scenario = ThreadLocal.withInitial(() -> null);
    private static final ThreadLocal<String> currentFeatureUri = ThreadLocal.withInitial(() -> "");
    private static final ThreadLocal<String> currentFeatureName = ThreadLocal.withInitial(() -> "");

    public static void setScenario(Scenario newScenario) {
        scenario.set(newScenario);
    }

    public static Scenario getScenario() {
        return scenario.get();
    }

    public static String getScenarioName() {
        return scenario.get().getName();
    }

    public static void attach(byte[] data, String mediaType) {
        scenario.get().attach(data, mediaType, null);
    }

    public static void attach(String data, String mediaType) {
        scenario.get().attach(data, mediaType, null);
    }

    public static void attach(byte[] data, String mediaType, String name) {
        scenario.get().attach(data, mediaType, name);
    }

    public static void attach(String data, String mediaType, String name) {
        scenario.get().attach(data, mediaType, name);
    }

    public static String getScreenshotTemplate() {
        return getScenario().getName().replaceAll(" ", "_");
    }

    public static String getFeatureName() {
        if (!sameFeature()) {
            currentFeatureUri.set(getScenario().getUri().getPath());
            List<String> list = TextFileHelper.readFile(new File(currentFeatureUri.get()));
            String newFeatureName = null;
            for (String line : list) {
                if (line.contains("Feature:")) {
                    newFeatureName = line.replace("Feature:", "").trim();
                }
            }
            if (newFeatureName != null) {
                currentFeatureName.set(newFeatureName);
            } else {
                throw new TestException("Unable to get feature name");
            }
        }
        return currentFeatureName.get();
    }

    public static Boolean sameFeature() {
        return currentFeatureUri.get().equals(getScenario().getUri().getPath());
    }

    public static String getFeatureId() {
        return getFeatureName().toLowerCase().replace(" ", "-");
    }
}
