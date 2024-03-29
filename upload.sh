#!/bin/bash
# This script will upload the file to the online judge and get the result

# Usage: ./upload.sh <STUDENT_ID> <HW>

# CONST
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m' # No Color

# load .env file
if [ -f .env ]; then
    export $(cat .env | xargs)
else
    echo -e "${RED}Error: .env file not found${NC}"
    echo -e "Please follow README.md to create a .env file with the following content:"
    echo -e "JUDGE_URL=<URL>"
    exit 1
fi

# parse the command line arguments
if [ $# -ne 2 ]; then
    echo -e "${RED}Error: Invalid number of arguments${NC}"
    cat <<EOF
Usage: $0 <STUDENT_ID> <HW>

    STUDENT_ID: should be the upper case of the student ID
    HW: the homework name, e.g., HW1, HW2, HW3, etc.
        should be the upper case !
EOF
    exit 1
fi

STUDENT_ID=$1
HW=$2
# HW_ID is `HW` in lowercase
HW_ID=$(echo -e $HW | tr '[:upper:]' '[:lower:]')

# upload the file to the online judge
curl \
    -F "file=@$STUDENT_ID.zip" \
    -F "studentID=$STUDENT_ID" \
    -F "hwID=$HW_ID" \
    -F "mode=stdin" \
    $JUDGE_URL > upload.log

success_result=$( \
grep -o 'showJudgeResult([^)]*)' upload.log | \
awk -F '[()]' '{split($2, params, ", "); for (i in params) print params[i]}'
)

# if the success result is not empty, print the result
if [ ! -z "$success_result" ]; then
    numPassed=$(echo -e $success_result | awk '{print $1}')
    numTestCases=$(echo -e $success_result | awk '{print $2}')
    score=$(echo -e $success_result | awk '{print $3}')
    runTime=$(echo -e $success_result | awk '{print $4}')
    if [ $numPassed -eq $numTestCases ]; then
        echo -e "${GREEN}$HW All correct${NC}"
    else
        echo -e "${YELLOW}$HW Partial correct${NC}"
    fi
    echo -e "Total test cases: \t$numTestCases"
    echo -e "Passed test cases: \t$numPassed"
    echo -e "Score: \t\t\t$score"
    echo -e "Run time: \t\t$runTime ms"
    exit 0
fi

fail_result=$( \
grep -o 'showErrorMessage([^)]*)' upload.log | \
awk -F '[()]' '{split($2, params, ", "); for (i in params) print params[i]}' | \
awk -F '<br/>' '{for (i=1; i<=NF; i++) print $i}'
)

if [ ! -z "$fail_result" ]; then
    echo -e "${RED}$HW Fail${NC}"
    echo -e "Error details: \n\n$fail_result"
    exit 0
fi