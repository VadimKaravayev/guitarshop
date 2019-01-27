package com.epam.preprod.karavayev.web.filter;

import static org.junit.Assert.*;

import com.epam.preprod.karavayev.web.filter.locale.storage.LocaleStorage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class LocaleFilterTest {

    /*@Mock
    private ServletRequest request;
    @Mock
    private ServletResponse response;
    @Mock
    private List<Locale> locales = new ArrayList<>();
    @Mock
    private Locale defaultLocale;
    @Mock
    private LocaleStorage localeStorage;
    @InjectMocks
    private LocaleFilter localeFilter;*/

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void name() {
        System.out.println("test");
    }
}