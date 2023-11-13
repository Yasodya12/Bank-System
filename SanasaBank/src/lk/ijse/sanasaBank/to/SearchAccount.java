package lk.ijse.sanasaBank.to;

public class SearchAccount {
   private String searchId;

    public SearchAccount() {
    }

    public SearchAccount(String searchId) {
        this.searchId = searchId;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    @Override
    public String toString() {
        return "SearchAccount{" +
                "searchId='" + searchId + '\'' +
                '}';
    }
}
