################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/ChangPA8.cpp \
../src/Student.cpp \
../src/TheSort.cpp 

CPP_DEPS += \
./src/ChangPA8.d \
./src/Student.d \
./src/TheSort.d 

OBJS += \
./src/ChangPA8.o \
./src/Student.o \
./src/TheSort.o 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp src/subdir.mk
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


clean: clean-src

clean-src:
	-$(RM) ./src/ChangPA8.d ./src/ChangPA8.o ./src/Student.d ./src/Student.o ./src/TheSort.d ./src/TheSort.o

.PHONY: clean-src

