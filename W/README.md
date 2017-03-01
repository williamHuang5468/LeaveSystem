# LeaveSystem

A command line tool for taking leave system.

Function:

- add a data
- list someone
- listall
- delete a data
- update a data
- query some data by name

## Example

    takeleave add william 2016-10-01 2016-10-02

    takeleave list william

    takeleave listall

    takeleave delete <leaveID>

    takeleave update <leaveID> 2016-01-01 2016-01-05

### querybyname


    takeleave querybyname william timefrom>2016-01-01
    takeleave querybyname william timeend<2020-01-01
    takeleave querybyname william timerange=2000-01-01,2222-01-01

## Function

    takeleave add <name> <time-from> <time-end>

    takeleave list <name>

| leaveId | time-from | time-end |
|---------|-----------|----------|
|    1    | 2016-10-01|2016-10-02|
|    2    | 2016-11-05|2016-11-08|

    takeleave listall

| name    | time-from | time-end |
|---------|-----------|----------|
| william | 2016-10-01|2016-10-02|
| john    | 2016-11-05|2016-11-08|


    takeleave delete <leaveID>

    takeleave update <leaveID> <time-from> <time-end>

### querybyname

    takeleave querybyname <name> <condition>

supported condition

- timefrom>2016-01-01
- timeend<2020-01-01
- timerange=2000-01-01,2222-01-01

> leaveID should be `unique` in whole database.
