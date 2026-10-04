package service;

public interface AdminManageService {
    /**
     * Number of uploads per similarity band:
     * [0] 0-19%, [1] 20-39%, [2] 40-59%, [3] 60-79%, [4] 80-99%, [5] 100% (exact).
     */
    int[] getSimilarityValue();
}
