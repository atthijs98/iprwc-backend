package iprwc;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import iprwc.configs.FileConfig;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.nio.charset.StandardCharsets;

public class iprwcConfiguration extends Configuration {
    @NotNull
    private String jwtSecret;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    private FileConfig fileConfig;

    @JsonProperty("jwtSecret")
    public byte[] getJwtSecret() {
        return jwtSecret.getBytes(StandardCharsets.UTF_8);
    }

    @JsonProperty("jwtSecret")
    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @JsonProperty("database")
    public DataSourceFactory getDatabase() {
        return database;
    }

    @JsonProperty("database")
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    @JsonProperty("files")
    public FileConfig getFileConfig() {
        return fileConfig;
    }

    @JsonProperty("files")
    public void setImageConfig(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }
}
