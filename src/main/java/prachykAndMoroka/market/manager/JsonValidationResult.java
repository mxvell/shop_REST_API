package prachykAndMoroka.market.manager;

public class JsonValidationResult {
    private boolean isValid;
    private String errorMsg;

    public JsonValidationResult(boolean isValid, String errorMsg) {
        this.isValid = isValid;
        this.errorMsg = errorMsg;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
