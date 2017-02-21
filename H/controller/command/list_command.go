package command

import (
	"fmt"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
)

// ListCommand - type for list command
type ListCommand struct {
	Args []string
}

// Execute - command to be executed
func (command ListCommand) Execute() {
	name := command.Args[0]
	fmt.Printf("%s\t\t\t\t%s\t%s\n", "leaveID", "time-from", "time-end")
	leaves := model.GetLeavesByName(name)
	for _, leave := range leaves {
		fmt.Printf("%s\t%s\t%s\n", leave.GetID(), leave.GetTimeFrom(), leave.GetTimeEnd())
	}
}
