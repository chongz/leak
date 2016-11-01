package chongz.leak;

import org.junit.Test;

import rx.functions.Action1;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        String[] names = new String[]{
            "zc", "mom", "beauty"
        };

        rx.Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                System.out.println(name);
            }
        });
    }
}