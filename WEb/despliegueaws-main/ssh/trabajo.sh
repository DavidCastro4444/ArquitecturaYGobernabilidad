#!/bin/sh
aws ec2 delete-key-pair --key-name Pruebakey
rm Pruebakey.pem
aws ec2 create-key-pair --key-name Pruebakey --query 'KeyMaterial' --output text > Pruebakey.pem
chmod 600 Pruebakey.pem

id_VPC=$(aws ec2 create-vpc --cidr-block 10.0.0.0/16 --query Vpc.VpcId --output text)
aws ec2 create-subnet --vpc-id $id_VPC --cidr-block 10.0.1.0/24 --output text
        echo "=============================="
		echo id_VPC
		echo "=============================="


id_Gateway=$(aws ec2 create-internet-gateway --query InternetGateway.InternetGatewayId --output text)
aws ec2 attach-internet-gateway --vpc-id $id_VPC --internet-gateway-id $id_Gateway
        echo "=============================="
		echo id_Gateway
        echo "=============================="


id_Route_Table=$(aws ec2 create-route-table --vpc-id "$id_VPC" --query RouteTable.RouteTableId --output text)
aws ec2 create-route --route-table-id $id_Route_Table --destination-cidr-block 0.0.0.0/0 --gateway-id $id_Gateway
aws ec2 describe-route-tables --route-table-id $id_Route_Table --output text
        echo "=============================="
		echo id_Route_Table
        echo "=============================="

id_Subnet=$(aws ec2 describe-subnets --filters "Name=vpc-id","Values='$id_VPC'" --query "Subnets[0].{ID:SubnetId}" --output text)
aws ec2 associate-route-table  --subnet-id $id_Subnet --route-table-id $id_Route_Table
aws ec2 modify-subnet-attribute --subnet-id $id_Subnet --map-public-ip-on-launch
        echo "=============================="
		echo id_Subnet
        echo "=============================="

aws ec2 create-security-group --group-name my-sg-cli --description "Test Security Group" --vpc-id $id_VPC
        echo "=============================="
		echo id_vpc
        echo "=============================="

aws ec2 describe-security-groups --filters Name=group-name,Values=my-sg-cli --query "SecurityGroups[*].{ID:GroupId}" --output text

id_sec_group=$(aws ec2 describe-security-groups --filters Name=group-name,Values=my-sg-cli --query "SecurityGroups[*].{ID:GroupId}" --output text)

aws ec2 authorize-security-group-ingress --group-id $id_sec_group --protocol tcp --port 3389 --cidr 0.0.0.0/0
        echo "=============================="
		echo id_sec_group
        echo "=============================="

aws ec2 authorize-security-group-ingress --group-id $id_sec_group --protocol tcp --port 22 --cidr 0.0.0.0/0

aws ec2 run-instances --image-id ami-032930428bf1abbff --count 1 --instance-type t2.micro --key-name Pruebakey --security-group-ids $id_sec_group --subnet-id $id_Subnet --tag-specifications 'ResourceType=instance,Tags=[{Key=NameInstance,Value=Instance1}]' 
id_instancia_1=$(aws ec2 describe-instances --filters "Name=tag:NameInstance,Values=Instance1"  --query "Reservations[].Instances[].InstanceId" --output text)

        echo "=============================="
		echo Prueba finalizada
        echo "=============================="
