package service;

import bean.DataSetBean;
import bean.UploadResult;
import java.util.List;

public interface ManageDataSetService {
    /** Inline deduplication: store the data only if it is unique enough. */
    UploadResult uploadDataSet(DataSetBean bean);

    /** Data stored by this user. */
    List<DataSetBean> getMyPicked(String uname);
}
