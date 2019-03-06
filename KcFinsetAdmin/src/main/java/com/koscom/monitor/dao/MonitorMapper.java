package com.koscom.monitor.dao;

import com.koscom.monitor.model.Ad01SegmentInfo;

public interface MonitorMapper {
	Ad01SegmentInfo getPushAD01Info(Ad01SegmentInfo ad01SegmentInfo) ;
	void insertMonitorHist(Ad01SegmentInfo ad01SegmentInfo) ;
	void updateMonitorHist(Ad01SegmentInfo ad01SegmentInfo) ;
}