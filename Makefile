# PJC C03 by Rafał Pocztarski
# https://github.com/rsp/rsp-skj-p02
# https://gitlab.com/rsp/rsp-skj-p02

CXXFLAGS = -g -std=c++1z -Wall -Wfatal-errors

ALL = PortMapper
# SumClient SumServer

all:
	+$(MAKE) -C PortMapper
	+$(MAKE) -C SumServer
	+$(MAKE) -C SumClient

clean:
	+$(MAKE) -C PortMapper clean
	+$(MAKE) -C SumServer clean
	+$(MAKE) -C SumClient clean

test: all
	./test.sh
