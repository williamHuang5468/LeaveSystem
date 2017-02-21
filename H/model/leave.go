package model

import (
	"fmt"

	"gopkg.in/mgo.v2/bson"
)

// Leave For takeleave record model
type Leave struct {
	BsonID   bson.ObjectId `bson:"_id"`
	Name     string        `bson:"name"`
	TimeFrom string        `bson:"timefrom"`
	TimeEnd  string        `bson:"timeend"`
}

// GetBsonID - Get Leave Bson ID
func (leave Leave) GetBsonID() bson.ObjectId {
	return leave.BsonID
}

// GetID - Get Leave ID
func (leave Leave) GetID() string {
	return leave.BsonID.Hex()
}

// GetName - Get GetName
func (leave Leave) GetName() string {
	return leave.Name
}

// GetTimeFrom - Get TimeFrom
func (leave Leave) GetTimeFrom() string {
	return leave.TimeFrom
}

// GetTimeEnd - Get TimeEnd
func (leave Leave) GetTimeEnd() string {
	return leave.TimeEnd
}

// SetTimeFrom - Set TimeFrom
func (leave *Leave) SetTimeFrom(timeFrom string) {
	leave.TimeFrom = timeFrom
}

// SetTimeEnd - Set TimeEnd
func (leave *Leave) SetTimeEnd(timeEnd string) {
	leave.TimeEnd = timeEnd
}

// Delete - delete leave
func (leave Leave) Delete() bool {
	leaveDAO := new(LeaveDAO)
	result := leaveDAO.DeleteLeave(leave.BsonID.Hex())
	return result
}

// Save = Save to DB
func (leave Leave) Save() bool {
	leaveDAO := new(LeaveDAO)
	var result bool
	if Exist(leave.BsonID.Hex()) {
		result = leaveDAO.UpdateLeave(leave)
	} else {
		result = leaveDAO.CreateLeave(&leave)
	}
	return result
}

// ToString
func (leave Leave) String() string {
	return fmt.Sprintf("bsonId:%s, name:%s", leave.BsonID.Hex(), leave.Name)
}

// GetLeavesByName - get Leaves by username
func GetLeavesByName(name string) []Leave {
	leaveDAO := new(LeaveDAO)
	leaves := leaveDAO.GetLeavesByName(name)
	return leaves
}

// GetLeaveByID - get Leave by id
func GetLeaveByID(id string) (Leave, error) {
	leaveDAO := new(LeaveDAO)
	leave, err := leaveDAO.GetLeaveByID(id)
	return leave, err
}

// GetLeaves - get all leaves
func GetLeaves() []Leave {
	leaveDAO := new(LeaveDAO)
	leaves := leaveDAO.GetLeaves()
	return leaves
}

// Exist - Check if document exist
func Exist(id string) bool {
	if !bson.IsObjectIdHex(id) {
		return false
	}
	leaveDAO := new(LeaveDAO)
	_, err := leaveDAO.GetLeaveByID(id)
	if err != nil {
		return false
	}
	return true
}
