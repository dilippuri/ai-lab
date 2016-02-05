clear all;
close all;
delete ('extended_iris_data.txt');
data = load('iris_data_norm_train.txt'); 		%load data into data matrix
[iris_row iris_col]=size(data); 			%size of input data
for i=1:iris_row
	x=data(i,1:iris_col);
	z=[x(1) x(2) x(3) x(4) x(1)*x(2) x(2)*x(3) x(3)*x(4) x(4)*x(1) x(1)^2 x(2)^2 x(3)^2 x(4)^2 x(5)];
	dlmwrite("extended_iris_data.txt", z,"-append");
end


data_train = load('extended_iris_data.txt'); %load train data into matrix
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
	x=data_test(i,1:test_col-1);
	z=[x(1) x(2) x(3) x(4) x(1)*x(2) x(2)*x(3) x(3)*x(4) x(4)*x(1) x(1)^2 x(2)^2 x(3)^2 x(4)^2];
	h=sign(z*w);
	y=data_test(i,test_col);
	if(y!=h)
		eout++;
	endif
endfor
weight_final = w;
dlmwrite("weight.txt", weight_final,"-append");
