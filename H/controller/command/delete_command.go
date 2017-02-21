package command

import (
	"fmt"
	"os"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/model"
	"gopkg.in/mgo.v2/bson"
)

// DeleteCommand - type for delete command
type DeleteCommand struct {
	Args []string
}

func (command DeleteCommand) checkParam() (id string) {
	args := command.Args
	if len(args) != 1 {
		os.Exit(1)
	} else if !bson.IsObjectIdHex(args[0]) {
		fmt.Println("you input a wrong format id")
		os.Exit(1)
	}
	return args[0]
}

// Execute - command to be executed
func (command DeleteCommand) Execute() {
	leaveID := command.checkParam()
	leave, err := model.GetLeaveByID(leaveID)
	if err != nil {
		fmt.Println("Leave Not Found!")
		os.Exit(1)
	}
	result := leave.Delete()
	if result {
		fmt.Printf("Delete %s Success!\n", leaveID)
	} else {
		fmt.Println("Leave Delete Fail!")
	}
}
