public class AssertKeyword {
    public static void main(String[] args) {
        assert 1 == 2; // NO FAILURE
        // qq: why doesn't assert 1 == 2; throw AssertionError as expected?
        /**
         * It's NOT throwing an AssertionError because assertions are disabled by default in JVM.
         * To make assertions work (throw an error when false), you must explicitly enable them at runtime using a specific command-line flag.
         * You need to pass the -enableassertions (or the shorter -ea) flag
         *
         * javac YourProgramName.java
         * java -ea YourProgramName
         */
        // Tip: In case you're not using javac/java commands for your app, pass -ea in VM options.
        // ref: https://github.com/abhinavgpt1/Learn-JavaFX/blob/master/Readme.md#final-vm-options
    }
}
