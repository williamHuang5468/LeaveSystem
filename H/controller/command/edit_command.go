package command

import (
	"fmt"
	"os"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/controller/logic"
	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
	"gopkg.in/mgo.v2/bson"
)

// EditCommand - type for edit command
type EditCommand struct {
	Args []string
}

func (command EditCommand) checkParam() (id, timeFrom, timeEnd string) {
	args := command.Args
	if len(args) != 3 {
		os.Exit(1)
	} else if !bson.IsObjectIdHex(args[0]) {
		fmt.Println("you input a wrong format id !")
		os.Exit(1)
	} else if !logic.IsTimeFormatValid(args[1]) {
		fmt.Println("The format of time-from should be 2016-11-16 !")
		os.Exit(1)
	} else if !logic.IsTimeFormatValid(args[2]) {
		fmt.Println("The format of time-end should be 2016-11-16 !")
		os.Exit(1)
	} else if !logic.IsTimePeriodValid(args[1], args[2]) {
		fmt.Println("You input invalid time-from and time-end !")
		os.Exit(1)
	}
	return args[0], args[1], args[2]
}

// Execute - command to be executed
func (command EditCommand) Execute() {
	id, timeFrom, timeEnd := command.checkParam()

	leave, err := model.GetLeaveByID(id)
	isOverlap := logic.IsNewRecordOverlapByNameOnEdit(id, timeFrom, timeEnd)

	if err != nil {
		fmt.Printf("Leave %s is not exist!", id)
		os.Exit(1)
	} else if isOverlap {
		fmt.Println("Edit Leave Failed!")
		fmt.Println("The time-from and time-end is overlapped with existed leave !")
		os.Exit(1)
	} else if (leave.GetTimeFrom() == timeFrom) && (leave.GetTimeEnd() == timeEnd) {
		fmt.Println("Edit Leave Failed!")
		fmt.Println("You should't update leave with same time-from and time-end !")
		os.Exit(1)
	}

	// Save leave
	leave.SetTimeFrom(timeFrom)
	leave.SetTimeEnd(timeEnd)
	result := leave.Save()

	if result {
		fmt.Println("Edit Leave Success!")
	} else {
		fmt.Println("Edit Leave Failed!")
	}
}
