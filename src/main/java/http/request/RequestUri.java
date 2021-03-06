package http.request;

import java.util.Objects;

public class RequestUri {
    private static final String DELIMITER = "\\?";

    private final String path;
    private final Parameters parameters;

    private RequestUri(String path, Parameters parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    private RequestUri(String path) {
        this(path, null);
    }

    public static RequestUri from(String requestUri) {
        Objects.requireNonNull(requestUri);
        if (hasQueryParameters(requestUri)) {
            return new RequestUri(requestUri);
        }
        String[] tokens = requestUri.split(DELIMITER);
        return new RequestUri(tokens[0], Parameters.from(tokens[1]));
    }

    private static boolean hasQueryParameters(String requestUri) {
        return requestUri.split(DELIMITER).length == 1;
    }

    public String getPath() {
        return path;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
