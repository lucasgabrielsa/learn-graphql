package com.lucasgabriel.learngraphql.resolver.bank.mutation;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UploadFileMutation implements GraphQLMutationResolver {

    // DataFetchingEnvironment must be the last parameter
    public UUID uploadFile(DataFetchingEnvironment environment) {
      log.info("Uploading File");

      DefaultGraphQLServletContext context = environment.getContext();

        List<Part> fileParts = context.getFileParts();
        fileParts.forEach(part -> log.info("Uploading: {} , size: {}", part.getSubmittedFileName(), part.getSize()));

        return UUID.randomUUID();
    }
}
