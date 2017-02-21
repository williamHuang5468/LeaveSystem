package controller

import (
	"fmt"
	"os"

	commands "gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/controller/command"
)

const (
	addCommand         string = "add"         // Add Command
	listCommand        string = "list"        // List Command
	listallCommand     string = "listall"     // Listall Command
	deleteCommand      string = "delete"      // Delete Command
	editCommand        string = "edit"        // Edit Command
	queryByNameCommand string = "querybyname" // QueryByName Command
)

var usage = `TakeLeave is a leave system for HopeBayTech.

Usage: takeleave [options...]

Options:

    add     [name] [time]             Add new records.
    list    [name]                    List records by name.
    listall                           List all records.
    delete  [leaveId]                 Delete a record by leaveId.
    edit    [leaveId]  [time] [time]  Edit a record.
    querybyname [name] [query]        Query records by some condition.

`

// Controller struct
type Controller struct {
	Args []string
}

// UserInputControl Process user input
func (controller Controller) UserInputControl() {
	// Check argumants length should > 0
	args := controller.Args
	if len(args) == 0 {
		fmt.Print(usage)
		os.Exit(1)
	}

	// Process command
	executor := new(Executor)

	switch args[0] {
	case addCommand:
		command := commands.AddCommand{Args: args[1:]}
		executor.Execute(command)
	case listCommand:
		command := commands.ListCommand{Args: args[1:]}
		executor.Execute(command)
	case listallCommand:
		command := commands.ListallCommand{Args: args[1:]}
		executor.Execute(command)
	case deleteCommand:
		command := commands.DeleteCommand{Args: args[1:]}
		executor.Execute(command)
	case editCommand:
		command := commands.EditCommand{Args: args[1:]}
		executor.Execute(command)
	case queryByNameCommand:
		command := commands.QuerybynameCommand{Args: args[1:]}
		executor.Execute(command)
	default:
		println("Command Not Found!")
		os.Exit(1)
	}
}
