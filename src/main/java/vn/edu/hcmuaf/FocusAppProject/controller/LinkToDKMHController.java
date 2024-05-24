package vn.edu.hcmuaf.FocusAppProject.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.LinkToDKMHDTO;
import vn.edu.hcmuaf.FocusAppProject.response.LinkToDKMHResponse;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.LinkToDKMHImp;

@RestController
@RequestMapping("/api/dkmh")
public class LinkToDKMHController {

    @Autowired
    LinkToDKMHImp linkToDKMHImp;
    @GetMapping("/is-linked/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> isLinked(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(linkToDKMHImp.isLinked(id));
    }
    @PostMapping("/link")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> linkToDKMH(@RequestBody @Valid LinkToDKMHDTO linkToDKMHDTO) throws Exception {
        linkToDKMHImp.createOrUpdateLinkToDKMH(linkToDKMHDTO);
        return ResponseEntity.ok("Link to DKMH successfully");
    }
    @GetMapping("/get-expire/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LinkToDKMHResponse> getExpire(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(linkToDKMHImp.getExpire(id));
    }
}