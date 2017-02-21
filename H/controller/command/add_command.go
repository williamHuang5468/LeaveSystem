package command

import (
	"fmt"
	"os"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/controller/logic"
	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
)

// AddCommand - type for add command
type AddCommand struct {
	Args []string
}

func (command AddCommand) checkParam() (name, timeFrom, timeEnd string) {
	args := command.Args
	if len(args) != 3 {
		os.Exit(1)
	} else if !logic.IsTimeFormatValid(args[1]) {
		fmt.Println("The format of time-from should be 2016-11-16")
		os.Exit(1)
	} else if !logic.IsTimeFormatValid(args[2]) {
		fmt.Println("The format of time-end should be 2016-11-16")
		os.Exit(1)
	} else if !logic.IsTimePeriodValid(args[1], args[2]) {
		fmt.Println("You input invalid time-from and time-end")
		os.Exit(1)
	}
	return args[0], args[1], args[2]
}

// Execute - command to be executed
func (command AddCommand) Execute() {
	name, timeFrom, timeEnd := command.checkParam()

	isOverlap := logic.IsNewRecordOverlapByName(name, timeFrom, timeEnd)

	if isOverlap {
		fmt.Println("Add Leave Failed!")
		fmt.Println("The time-from and time-end is overlapped with existed leave")
		os.Exit(1)
	}

	newLeave := model.Leave{
		Name:     name,
		TimeFrom: timeFrom,
		TimeEnd:  timeEnd,
	}

	// Save leave
	result := newLeave.Save()
	if result {
		fmt.Println("Add Leave Success!")
	} else {
		fmt.Println("Add Leave Failed!")
	}
}
