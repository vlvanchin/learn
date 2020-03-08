#!/usr/bin/zsh 

# Create directory
TODAY=`date '+%Y%m%d'`
output_dir="results_$TODAY"
mkdir $output_dir
if [ $? -eq 0 -o -d $output_dir ] ; then
	echo "$output_dir found "
else 
	echo "$output_dir folder not found"
fi
