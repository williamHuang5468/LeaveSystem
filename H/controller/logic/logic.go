package logic

import (
	"regexp"
	"time"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
)

const (
	timeLayout            = "2006-01-02"
	timeRegularExpreesion = "^\\d{4}-\\d{2}-\\d{2}$"
)

// IsTimeFormatValid - Time format should be 2011-11-11
func IsTimeFormatValid(timeString string) bool {
	isMatch, err := regexp.MatchString(timeRegularExpreesion, timeString)
	if err != nil {
		return false
	}
	return isMatch
}

// IsTimePeriodValid - Check Time period is valid
func IsTimePeriodValid(startDate, endDate string) bool {
	startDateTime, err1 := time.Parse(timeLayout, startDate)
	endDateTime, err2 := time.Parse(timeLayout, endDate)
	if err1 != nil || err2 != nil {
		return false
	}
	if endDateTime.After(startDateTime) {
		return true
	}
	return false
}

// IsPeriodOverlap - Check is given period overlap
func IsPeriodOverlap(startDate1, endDate1, startDate2, endDate2 string) bool {
	startDate1Time, _ := time.Parse(timeLayout, startDate1)
	endDate1Time, _ := time.Parse(timeLayout, endDate1)
	startDate2Time, _ := time.Parse(timeLayout, startDate2)
	endDate2Time, _ := time.Parse(timeLayout, endDate2)
	if (startDate1Time.Unix() <= endDate2Time.Unix()) && (endDate1Time.Unix() >= startDate2Time.Unix()) {
		return true
	}
	return false
}

// IsNewRecordOverlapByName - Check if record overlapped with existed records
func IsNewRecordOverlapByName(name, timeFrom, timeEnd string) bool {
	leaves := model.GetLeavesByName(name)

	for _, leave := range leaves {
		isOverlap := IsPeriodOverlap(timeFrom, timeEnd, leave.GetTimeFrom(), leave.GetTimeEnd())

		if isOverlap {
			return true
		}
	}
	return false
}

// IsNewRecordOverlapByNameOnEdit - Check if record overlapped with existed records (On edit a record)
func IsNewRecordOverlapByNameOnEdit(id, timeFrom, timeEnd string) bool {
	leave, _ := model.GetLeaveByID(id)
	leaves := model.GetLeavesByName(leave.GetName())

	for _, leave := range leaves {
		if id == leave.GetID() {
			continue
		}
		isOverlap := IsPeriodOverlap(timeFrom, timeEnd, leave.GetTimeFrom(), leave.GetTimeEnd())
		if isOverlap {
			return true
		}
	}
	return false
}
