#!/bin/sh
export LANG=ko_KR.eucKR


###################
#   User Option   #
###################

if [ "$#" -lt 1 ]
then
  echo " ";
  echo "Switch Error";
  echo " ";
  exit;
fi


## get a base_sh_dir variable where this shell script located  start
if test `expr index "$0" /` -eq 1
then
        base_sh_dir=`dirname $0`
else
        curr_sh_dir=`pwd`; sh_file_name=`basename $0`; txt=$curr_sh_dir/$sh_file_name; txt_conv=${txt/$sh_file_name/}
        txt2=$txt_conv$0 ; txt2_conv=${txt2/$sh_file_name/}; base_sh_dir=`cd $txt2_conv;pwd`
fi
## get a base_sh_dir varialbe end


cronjob_class="ProxyServer"
java_exec="java"

log_dir=$base_sh_dir"/logs"/$1;
log_file=$1_`date +%Y%m%d.log`
echo "Log Dir=>" $log_dir

if [ ! -d $log_dir ]
then
   mkdir -p $log_dir
fi

context_path=$base_sh_dir

echo "CONTEXT PATH = " $context_path


java_class_path="./:$context_path";


#$java_exec -DCZPWD=${CZPWD} -DCZBAK_DEL=${CZBAK_DEL} -cp $java_class_path $cronjob_class $* >> $log_dir/$log_file 2>&1
$java_exec -DCZPWD=${CZPWD} -DCZBAK_DEL=${CZBAK_DEL} -cp $java_class_path $cronjob_class $*