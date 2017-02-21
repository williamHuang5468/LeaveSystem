package logic

import "testing"

//// IsTimeFormatValid Test

func TestIsTimeFormatValid_valid(t *testing.T) {
	testCases := []struct {
		dateSring string
	}{
		{"2016-11-15"},
		{"2016-01-15"},
		{"2016-01-01"},
	}

	for _, testCase := range testCases {
		status := IsTimeFormatValid(testCase.dateSring)
		if !status {
			t.Error(testCase.dateSring + " is Invalid")
		}
	}
}

func TestIsTimeFormatValid_invalid(t *testing.T) {
	testCases := []struct {
		dateSring string
	}{
		{"2016-1-15"},
		{"2016-01-5"},
		{"201-01-01"},
		{"2016-01"},
		{"2016/11/11"},
		{"2016"},
		{""},
	}

	for _, testCase := range testCases {
		status := IsTimeFormatValid(testCase.dateSring)
		if status {
			t.Error(testCase.dateSring + " should be invelid")
		}
	}
}

//// IsTimePeriodValid Test

func TestIsTimePeriodValid(t *testing.T) {
	status := IsTimePeriodValid("2016-11-11", "2016-12-11")
	if !status {
		t.Error("TestIsTimePeriodValid failed")
	}
}

func TestIsTimePeriodValid_wrongFormat(t *testing.T) {
	status := IsTimePeriodValid("2016-1111", "201612-11")
	if status {
		t.Error("TestIsTimePeriodValid_wrongFormat failed")
	}
}

func TestIsTimePeriodValid_before(t *testing.T) {
	status := IsTimePeriodValid("2016-11-11", "2016-10-11")
	if status {
		t.Error("IsTimePeriodValid_before case failed")
	}
}

func TestIsTimePeriodValid_equal(t *testing.T) {
	status := IsTimePeriodValid("2016-11-11", "2016-11-11")
	if status {
		t.Error("IsTimePeriodValid_equal case failed")
	}
}

//// IsPeriodOverlap Test

func TestIsPeriodOverlap_notOverlap(t *testing.T) {
	isOverlap := IsPeriodOverlap("2016-01-01", "2016-05-01", "2016-05-02", "2016-06-01")
	if isOverlap {
		t.Error("TestIsPeriodOverlap_notOverlap case failed")
	}
}

func TestIsPeriodOverlap_bound(t *testing.T) {
	// EndDate1 is the same as startDate2
	isOverlap := IsPeriodOverlap("2016-01-01", "2016-05-01", "2016-05-01", "2016-06-01")
	if !isOverlap {
		t.Error("TestIsPeriodOverlap_bound case failed")
	}

	// StartDate1 is the same as EndDate2
	isOverlap = IsPeriodOverlap("2016-01-01", "2016-05-01", "2015-05-01", "2016-01-01")
	if !isOverlap {
		t.Error("TestIsPeriodOverlap_bound case failed")
	}
}

func TestIsPeriodOverlap_middle(t *testing.T) {
	isOverlap := IsPeriodOverlap("2016-01-01", "2016-05-01", "2016-04-01", "2016-06-01")
	if !isOverlap {
		t.Error("TestIsPeriodOverlap_middle case failed")
	}
}
