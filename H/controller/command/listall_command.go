package command

import (
	"fmt"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
)

// ListallCommand - type for listall command
type ListallCommand struct {
	Args []string
}

// Execute - command to be executed
func (command ListallCommand) Execute() {
	fmt.Printf("%s\t\t%s\t%s\n", "name", "time-from", "time-end")
	leaves := model.GetLeaves()
	for _, leave := range leaves {
		fmt.Printf("%s\t%s\t%s\n", leave.GetName(), leave.GetTimeFrom(), leave.GetTimeEnd())
	}
}
