package com.presentation.commons;

import com.domain.usecase.UseCaseError;
import com.domain.usecase.UseCaseResponse;
import com.presentation.UseCaseExecution;
import com.presentation.UseCaseInvoker;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class FakeUseCaseInvoker {

    private FakeUseCaseInvoker() {
    }

    public static UseCaseInvoker create() {
        UseCaseInvoker interactorInvoker = mock(UseCaseInvoker.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                UseCaseExecution execution = (UseCaseExecution) invocation.getArguments()[0];
                UseCaseResponse response = execution.getUseCase().call();
                UseCaseError error = response.getError();

                if (response.hasError() && execution.getInteractorErrorResult(error.getClass()) != null) {
                    execution.getInteractorErrorResult(error.getClass()).onResult(error);
                } else if (execution.getUseCaseResult() != null) {
                    execution.getUseCaseResult().onResult(response.getResult());
                }

                return null;
            }
        }).when(interactorInvoker).execute(anyInteractorExecution());

        return interactorInvoker;
    }

    private static UseCaseExecution<?> anyInteractorExecution() {
        return any();
    }
}
