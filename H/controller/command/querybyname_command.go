package command

import (
	"fmt"
	"os"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/controller/logic"
)

// QuerybynameCommand - type for querybyname command
type QuerybynameCommand struct {
	Args []string
}

func (command QuerybynameCommand) checkParam() logic.Query {
	args := command.Args
	if len(args) != 2 {
		os.Exit(1)
	}

	query, err := logic.NewLeaveQuery(args[0], args[1])
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}
	return query
}

// Execute - command to be executed
func (command QuerybynameCommand) Execute() {
	query := command.checkParam()
	leaves := query.Query()

	fmt.Printf("%s\t\t\t\t%s\t%s\n", "leaveID", "time-from", "time-end")
	for _, leave := range leaves {
		fmt.Printf("%s\t%s\t%s\n", leave.GetID(), leave.GetTimeFrom(), leave.GetTimeEnd())
	}
}
