package bean;

public class DataSetBean {
    private int id;
    private String from_user, dataset_name, dataset, uploaded_on;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFrom_user() { return from_user; }
    public void setFrom_user(String from_user) { this.from_user = from_user; }
    public String getDataset_name() { return dataset_name; }
    public void setDataset_name(String dataset_name) { this.dataset_name = dataset_name; }
    public String getDataset() { return dataset; }
    public void setDataset(String dataset) { this.dataset = dataset; }
    public String getUploaded_on() { return uploaded_on; }
    public void setUploaded_on(String uploaded_on) { this.uploaded_on = uploaded_on; }
}
