#!/bin/sh
export LANG=ko_KR.eucKR


###################
#   User Option   #
###################


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
log_file=fep_`date +%Y%m%d.log`
echo "Log Dir=>" $log_dir

if [ ! -d $log_dir ]
then
   mkdir -p $log_dir
fi

context_path=$base_sh_dir

echo "CONTEXT PATH = " $context_path


java_class_path="./:$context_path";


nohup $java_exec -DLCA_URL=http://10.30.50.11:9080/finance/statusNoti.crz  -cp $java_class_path $cronjob_class 9880 $* >> $log_dir/$log_file & 
echo "$!" > ProxyServer.pid
#$java_exec  -cp $java_class_path $cronjob_class 9880  $*
