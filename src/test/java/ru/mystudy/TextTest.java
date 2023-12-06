package ru.mystudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class TextTest {

    @Test
    @DisplayName("Тест кэширования аннотированных методов @Cache")
    public void testing_cache() {
        TextA mockTextA = Mockito.mock(TextA.class);

        Textable cacheTextA = Utils.cache(mockTextA);

        cacheTextA.print();
        cacheTextA.print();
        Mockito.verify(mockTextA, times(2)).print();

        cacheTextA.get();
        Mockito.verify(mockTextA, times(1)).get();

        cacheTextA.get();
        Mockito.verify(mockTextA, times(1)).get();

        cacheTextA.setText("text2");

        cacheTextA.get();
        Mockito.verify(mockTextA, times(2)).get();

        cacheTextA.print();
        Mockito.verify(mockTextA, times(3)).print();

        cacheTextA.get();
        Mockito.verify(mockTextA, times(2)).get();
    }
}
