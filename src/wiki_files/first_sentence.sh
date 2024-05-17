#!/bin/bash

# Run the script to get the filename
filename=$(./get_filename.sh $1)

# Extract the first sentence from the file
first_sentence=$(head -n 1 "$filename" | sed 's/^\s*//' | awk '{print $0; exit}')

echo "$first_sentence"
