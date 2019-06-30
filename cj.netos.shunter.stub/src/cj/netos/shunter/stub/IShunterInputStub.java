package cj.netos.shunter.stub;

import cj.netos.shunter.Message;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/input.service", usage = "分流输入端子")
public interface IShunterInputStub {
	@CjStubMethod(command = "post", usage = "输入分流器")
	void input(@CjStubInParameter(key = "labelFullName", usage = "标签全路径名") String labelFullName,
			@CjStubInContentKey(key = "message", usage = "消息") Message message) throws CircuitException;
}
