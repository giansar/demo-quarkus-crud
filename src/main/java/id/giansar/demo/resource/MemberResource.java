package id.giansar.demo.resource;

import id.giansar.demo.dto.MemberDto;
import id.giansar.demo.entity.Member;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/member")
public class MemberResource {
    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Member member = Member.findById(id);
        return Response.ok(member).build();
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        List<Member> memberList = Member.listAll();
        return Response.ok(memberList).build();
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addNewMember(MemberDto dto) {
        Member member = new Member();
        member.email = dto.email;
        member.fullName = dto.fullName;
        member.isAgreeTerms = dto.isAgreeTerms;
        member.password = dto.password;
        member.persistAndFlush();
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @Transactional
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response modifyMember(MemberDto dto) {
        Member member = Member.findById(dto.id);
        if (member == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(dto).build();
        }
        member.password = dto.password;
        member.isAgreeTerms = dto.isAgreeTerms;
        member.fullName = dto.fullName;
        member.email = dto.email;
        member.persistAndFlush();
        return Response.ok(dto).build();
    }

    @Transactional
    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response deleteMember(@PathParam("id") Long id) {
        Member member = Member.findById(id);
        if (member == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        member.delete();
        return Response.ok(member).build();
    }
}