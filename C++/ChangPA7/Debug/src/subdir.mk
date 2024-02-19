################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Beverage.cpp \
../src/ChangPA7.cpp 

CPP_DEPS += \
./src/Beverage.d \
./src/ChangPA7.d 

OBJS += \
./src/Beverage.o \
./src/ChangPA7.o 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp src/subdir.mk
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


clean: clean-src

clean-src:
	-$(RM) ./src/Beverage.d ./src/Beverage.o ./src/ChangPA7.d ./src/ChangPA7.o

.PHONY: clean-src

