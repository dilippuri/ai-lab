data_train=load('Iris_data_norm_train.txt');

for i=1:110
if(data_train(i,5)==1)
plot(i,data_train(i),'g')
hold on
endif
if(data_train(i,5)==-1)
plot(i,data_train(i),'r')
hold on
endif
end
