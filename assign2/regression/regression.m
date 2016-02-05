clear all;
close all;
data_train = load('iris_data_norm_train.txt'); %load train data into matrix
data_test = load('iris_data_norm_test.txt'); %load test data into matrix

[train_row train_col]=size(data_train); %size of input data
[test_row test_col]=size(data_test); %size of input data

train=train_row;	%train ratio
test=test_row;		%test ratio

x = data_train(:,1:train_col-1);
y = data_train(:,train_col);

w = inv(x' * x) * x' *y;
err_out(1) = 0;

eout=0;
		%testing data
for i=1:test
	x=data_test(i,1:train_col-1);
	h=sign(x*w);
	y=data_test(i,train_col);
	if(y!=h)
		eout++;
	endif
endfor
weight_final=w;
dlmwrite("weight.txt", weight_final,"-append");
