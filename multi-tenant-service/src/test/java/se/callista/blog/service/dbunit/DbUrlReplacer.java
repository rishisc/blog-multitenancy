package se.callista.blog.service.dbunit;

import com.github.database.rider.core.replacers.Replacer;
import org.dbunit.dataset.ReplacementDataSet;

import java.util.Objects;

public class DbUrlReplacer implements Replacer {

    @Override
    public void addReplacements(ReplacementDataSet dataSet) {
        String dbUrl = System.getProperty("DB_URL");
        if (dbUrl != null) {
            dataSet.addReplacementSubstring("${DB_URL}", dbUrl);
        }
        String dbHost = System.getProperty("DB_HOST");
        if (dbHost != null) {
            dataSet.addReplacementSubstring("${DB_HOST}", dbHost);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }
}