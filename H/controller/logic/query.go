package logic

import (
	"errors"
	"regexp"
	"strings"
	"time"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
)

const (
	timeFrom          string = "timefrom"
	timeEnd           string = "timeend"
	timeRange         string = "timerange"
	largerThanOperand string = ">"
	lessThanOperand   string = "<"
	equalOperand      string = "="
	rangeCama         string = ","
)

// Query - Type for Query by name
type Query interface {
	Query() []model.Leave
}

// LeaveQuery : type for Query
type LeaveQuery struct {
	Name     string // user name
	Keyword  string // timefrom, timeend, timerange
	Operator string // >, <, =
	Values   []string
}

// NewLeaveQuery ..
func NewLeaveQuery(name, queryString string) (Query, error) {
	if !isQueryValid(queryString) {
		return nil, errors.New("invalid query string : " + queryString)
	}

	var query LeaveQuery
	keyword, operator, value := getQueryParams(queryString)
	query = LeaveQuery{Name: name, Keyword: keyword, Operator: operator, Values: strings.Split(value, rangeCama)}
	return query, nil
}

// Query - Execute query
func (query LeaveQuery) Query() []model.Leave {
	name := query.Name
	keyword := query.Keyword
	values := query.Values

	// get leaves by name
	leaves := model.GetLeavesByName(name)

	var leaveList []model.Leave

	if keyword == timeFrom {
		targetTime, _ := time.Parse(timeLayout, values[0])
		for _, leave := range leaves {
			leaveTimeFromTime, _ := time.Parse(timeLayout, leave.GetTimeFrom())
			if leaveTimeFromTime.After(targetTime) {
				leaveList = append(leaveList, leave)
			}
		}
	} else if keyword == timeEnd {
		targetTime, _ := time.Parse(timeLayout, values[0])
		for _, leave := range leaves {
			leaveTimeEndTime, _ := time.Parse(timeLayout, leave.GetTimeEnd())
			if leaveTimeEndTime.Before(targetTime) {
				leaveList = append(leaveList, leave)
			}
		}
	} else if keyword == timeRange {
		targetTimeStart, _ := time.Parse(timeLayout, values[0])
		targetTimeEnd, _ := time.Parse(timeLayout, values[1])
		for _, leave := range leaves {
			leaveTimeFromTime, _ := time.Parse(timeLayout, leave.GetTimeFrom())
			leaveTimeEndTime, _ := time.Parse(timeLayout, leave.GetTimeEnd())

			if (leaveTimeFromTime.Equal(targetTimeStart) || leaveTimeFromTime.After(targetTimeStart)) &&
				(leaveTimeEndTime.Equal(targetTimeEnd) || leaveTimeEndTime.After(targetTimeEnd)) {
				leaveList = append(leaveList, leave)
			}
		}
	}
	return leaveList
}

func isQueryValid(queryString string) bool {
	regex1 := "^(timefrom|timeend)(>|<)\\d{4}-\\d{2}-\\d{2}$"
	regex2 := "^timerange=\\d{4}-\\d{2}-\\d{2},\\d{4}-\\d{2}-\\d{2}$"

	// Check if Query String match syntax
	isMatchRegexp1, _ := regexp.MatchString(regex1, queryString)
	isMatchRegexp2, _ := regexp.MatchString(regex2, queryString)
	if isMatchRegexp1 || isMatchRegexp2 {
		return true
	}
	return false
}

func getQueryParams(queryString string) (string, string, string) {
	var keyword string
	var operator string
	var value string

	if strings.Contains(queryString, largerThanOperand) {
		operator = largerThanOperand
	} else if strings.Contains(queryString, lessThanOperand) {
		operator = lessThanOperand
	} else if strings.Contains(queryString, equalOperand) {
		operator = equalOperand
	}

	// Get params
	params := strings.Split(queryString, operator)
	keyword = params[0]
	value = params[1]
	return keyword, operator, value
}
